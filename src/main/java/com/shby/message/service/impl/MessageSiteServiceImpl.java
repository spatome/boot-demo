package com.shby.message.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shby.api.dto.msg.MsgSiteMessageDto;
import com.shby.message.common.constants.MyCache;
import com.shby.message.common.enums.ChannelTypeEnum;
import com.shby.message.entity.MsgSiteMessage;
import com.shby.message.service.BaseService;
import com.shby.message.service.MessageSiteService;
import com.shby.message.util.SUtil;
import com.shby.message.util.convert.JUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageSiteServiceImpl extends BaseService implements MessageSiteService {

	@Transactional
	@Override
	public void execute(MsgSiteMessageDto dto) {
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
	private void doTemplatMessage(MsgSiteMessageDto dto) {
		String content = MyCache.SITE_TEMPLATE_MAP.get(dto.getTemplateId());
		if (StringUtils.isBlank(content)) {
			this.save(dto, false, "模板不存在");
			return;
		}
		String newContent = SUtil.updateContent(content, dto.getTemplateParams());
		dto.setMessageContent(newContent);
		this.save(dto, true, null);
	}

	/**
	 * 非模板消息
	 */
	private void doNotTemplatMessage(MsgSiteMessageDto dto) {
		this.save(dto, true, null);
	}

	private void save(MsgSiteMessageDto dto, boolean isSend, String descs) {
		Date date = new Date();
		if(isSend){
			//发送成功
			MsgSiteMessage newRecord = new MsgSiteMessage();
			newRecord.setCreateTime(date);
			newRecord.setUpdateTime(date);
			newRecord.setMsgId(dto.getMsgId());
			newRecord.setUserId(dto.getUserId());
			newRecord.setUserName(dto.getUserName());
			newRecord.setTemplateId(tranLong(dto.getTemplateId()));
			newRecord.setTempleteParams(dto.getTemplateParams());
			newRecord.setMessageTitle(dto.getMessageTitle());
			newRecord.setMessageContent(dto.getMessageContent());
			newRecord.setOperatorId(dto.getOperatorId());
			newRecord.setOperatorName(dto.getOperatorName());
			daoFactory.getMsgSiteMessageMapper().insertSelective(newRecord);			
		}else{
			// 发送失败
			String content = JUtil.toJson(dto);
			log.error(String.format("channelId:%s发送失败,内容:%s", "", content));
			super.saveMsgErrorLog(date, null, ChannelTypeEnum.SITE.name(), content, descs);
		}
	}

	@Override
	public boolean send(String to, String phone, String content) {
		return true;
	}
}
