package com.shby.message.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shby.api.dto.msg.MsgPhoneMessageDto;
import com.shby.message.common.constants.MyCache;
import com.shby.message.common.enums.ChannelTypeEnum;
import com.shby.message.entity.MsgPhoneMessage;
import com.shby.message.oapi.ResultBO;
import com.shby.message.oapi.smsyp.SmsSendTypeEnum;
import com.shby.message.service.BaseService;
import com.shby.message.service.MessagePhoneService;
import com.shby.message.util.SUtil;
import com.shby.message.util.convert.JUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 短信单发
 */
@Service
@Slf4j
public class MessagePhoneServiceImpl extends BaseService implements MessagePhoneService {

	@Transactional
	@Override
	public void execute(MsgPhoneMessageDto dto) {
		log.info("《==" + dto);
		if (StringUtils.isNotBlank(dto.getTemplateId())) {
			// 模板消息
			this.doTemplatMessage(dto);
		} else {
			this.doNotTemplatMessage(dto);
		}
	}

	/**
	 * 模板消息
	 */
	private void doTemplatMessage(MsgPhoneMessageDto dto) {
		String content = MyCache.SMS_TEMPLATE_MAP.get(dto.getTemplateId());
		if (StringUtils.isBlank(content)) {
			this.save(dto, false, MyCache.SMS_DTO.getChannelId(), "模板不存在");
			return;
		}
		String newContent = SUtil.updateContent(content, dto.getTemplateParams());
		ResultBO resultBO = this.send(dto.getPhoneNum(), newContent);
		this.save(dto, resultBO.isSend(), MyCache.SMS_DTO.getChannelId(), resultBO.getMessage());
	}

	/**
	 * 非模板消息
	 */
	private void doNotTemplatMessage(MsgPhoneMessageDto dto) {
		ResultBO resultBO = this.send(dto.getPhoneNum(), dto.getMessageContent());
		this.save(dto, resultBO.isSend(), MyCache.SMS_DTO.getChannelId(), resultBO.getMessage());
	}

	private void save(MsgPhoneMessageDto dto, boolean isSend, String channelId, String descs) {
		Date date = new Date();

		if (isSend) {
			// 发送成功
			MsgPhoneMessage newMsgPhoneMessage = new MsgPhoneMessage();
			newMsgPhoneMessage.setCreateTime(date);
			newMsgPhoneMessage.setUpdateTime(date);
			newMsgPhoneMessage.setMsgId(dto.getMsgId());
			newMsgPhoneMessage.setUserId(dto.getUserId());
			newMsgPhoneMessage.setUserName(dto.getUserName());
			newMsgPhoneMessage.setPhoneNum(dto.getPhoneNum());
			newMsgPhoneMessage.setTemplateId(tranLong(dto.getTemplateId()));
			newMsgPhoneMessage.setTempleteParams(dto.getTemplateParams());
			newMsgPhoneMessage.setMessageTitle(dto.getMessageTitle());
			newMsgPhoneMessage.setMessageContent(dto.getMessageContent());
			newMsgPhoneMessage.setChannelId(channelId);
			newMsgPhoneMessage.setOperatorId(dto.getOperatorId());
			newMsgPhoneMessage.setOperatorName(dto.getOperatorName());
			daoFactory.getMsgPhoneMessageMapper().insertSelective(newMsgPhoneMessage);
		} else {
			// 发送失败
			String content = JUtil.toJson(dto);
			log.error(String.format("channelId:%s发送失败,内容:%s", channelId, content));
			super.saveMsgErrorLog(date, channelId, ChannelTypeEnum.PHONE.getText(), content, descs);
		}
	}

	@Override
	public ResultBO send(String phone, String content) {
		return serviceFactory.getSmsBaseService().sendMessage(MyCache.SMS_DTO, SmsSendTypeEnum.SYSTEM, phone, content);
	}
}
