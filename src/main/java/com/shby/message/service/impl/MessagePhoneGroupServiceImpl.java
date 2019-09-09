package com.shby.message.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shby.api.dto.msg.MsgPhoneMessageGroupDto;
import com.shby.api.dto.msg.MsgPhoneMessageGroupDto.Data;
import com.shby.message.common.constants.MyCache;
import com.shby.message.common.enums.ChannelTypeEnum;
import com.shby.message.entity.MsgPhoneMessage;
import com.shby.message.oapi.smsyp.SmsSendTypeEnum;
import com.shby.message.service.BaseService;
import com.shby.message.service.MessagePhoneGroupService;
import com.shby.message.util.SUtil;
import com.shby.message.util.convert.JUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 短信群发
 */
@Service
@Slf4j
public class MessagePhoneGroupServiceImpl extends BaseService implements MessagePhoneGroupService {

	@Transactional
	@Override
	public void execute(MsgPhoneMessageGroupDto dto) {
		log.info("《==" + dto);
		if (dto.getDataList().size()>500) {
			this.save(dto, false, MyCache.SMS_DTO.getChannelId(), "超过数量500限制");
			return;
		}

		if (StringUtils.isNotBlank(dto.getTempletId())) {
			// 模板消息
			this.doTemplatMessage(dto);
		} else {
			// 非模板(自定义)消息
			this.doNotTemplatMessage(dto);
		}
	}

	/**
	 * 模板消息
	 */
	private void doTemplatMessage(MsgPhoneMessageGroupDto dto) {
		String content = MyCache.SMS_TEMPLATE_MAP.get(dto.getTempletId());
		if (StringUtils.isBlank(content)) {
			this.save(dto, false, MyCache.SMS_DTO.getChannelId(), "模板不存在");
			return;
		}
		String newContent = SUtil.updateContent(content, dto.getTempletParams());
		boolean isSend = this.send(this.getPhones(dto.getDataList()), newContent);
		this.save(dto, isSend, MyCache.SMS_DTO.getChannelId(), null);
	}

	/**
	 * 非模板消息
	 */
	private void doNotTemplatMessage(MsgPhoneMessageGroupDto dto) {
		boolean isSend = this.send(this.getPhones(dto.getDataList()), dto.getMessageContent());
		this.save(dto, isSend, MyCache.SMS_DTO.getChannelId(), null);
	}

	private void save(MsgPhoneMessageGroupDto dto, boolean isSend, String channelId, String descs) {
		Date date = new Date();
		if (isSend) {
			// 发送成功
			List<MsgPhoneMessage> msgPhoneMessageList = new ArrayList<MsgPhoneMessage>();
			for (Data data : dto.getDataList()) {
				MsgPhoneMessage newMsgPhoneMessage = new MsgPhoneMessage();
				newMsgPhoneMessage.setCreateTime(date);
				newMsgPhoneMessage.setUpdateTime(date);
				newMsgPhoneMessage.setMsgId(dto.getMsgId());
				newMsgPhoneMessage.setUserId(data.getUserId());
				newMsgPhoneMessage.setUserName(data.getUserName());
				newMsgPhoneMessage.setPhoneNum(data.getPhoneNum());
				newMsgPhoneMessage.setTemplateId(StringUtils.isBlank(dto.getTempletId()) ? null : Long.valueOf(dto.getTempletId()));
				newMsgPhoneMessage.setTempletParams(dto.getTempletParams());
				newMsgPhoneMessage.setMessageTitle(dto.getMessageTitle());
				newMsgPhoneMessage.setMessageContent(dto.getMessageContent());
				newMsgPhoneMessage.setChannelId(channelId);
				newMsgPhoneMessage.setOperatorId(dto.getOperatorId());
				newMsgPhoneMessage.setOperatorName(dto.getOperatorName());
				msgPhoneMessageList.add(newMsgPhoneMessage);
			}
			if (msgPhoneMessageList.size() > 0) {
				daoFactory.getMsgPhoneMessageMapper().batchInsert(msgPhoneMessageList);
			}
		} else {
			// 发送失败
			String content = JUtil.toJson(dto);
			log.error(String.format("channelId:%s发送失败,内容:%s", channelId, content));
			super.saveMsgErrorLog(date, channelId, ChannelTypeEnum.PHONE.getText(), content, descs);
		}
	}

	private String getPhones(List<Data> dataList){
		StringBuffer result = new StringBuffer();

		for (Data data : dataList) {
			result.append(data.getPhoneNum()).append(",");
		}

		return result.toString();
	}
	
	@Override
	public boolean send(String phone, String content) {
		return serviceFactory.getSmsBaseService().sendMessage(MyCache.SMS_DTO, SmsSendTypeEnum.SYSTEM, phone, content);
	}

}
