package com.shby.message.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.shby.api.dto.msg.MsgMailMessageDto;
import com.shby.message.common.constants.MyCache;
import com.shby.message.common.enums.ChannelTypeEnum;
import com.shby.message.entity.MsgEmailMessage;
import com.shby.message.service.BaseService;
import com.shby.message.service.MessageMailService;
import com.shby.message.util.SUtil;
import com.shby.message.util.convert.JUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 单发邮件
 */
@Service
@Slf4j
public class MessageMailServiceImpl extends BaseService implements MessageMailService {

	@Override
	public void execute(MsgMailMessageDto dto) {
		log.info("《=="+dto);

		if(StringUtils.isNotBlank(dto.getTemplateId())){
			//模板消息
			this.doTemplatMessage(dto);
		}else{
			this.doNotTemplatMessage(dto);
		}
	}

	/**
	 * 模板消息
	 */
	private void doTemplatMessage(MsgMailMessageDto dto){
		String content = MyCache.MAIL_TEMPLATE_MAP.get(dto.getTemplateId());
		if(StringUtils.isBlank(content)){
			this.save(dto, false, MyCache.MAIL_DTO.getChannelId(), "模板不存在");
			return;
		}
		String newContent = SUtil.updateContent(content, dto.getTemplateParams());
		boolean isSend = this.send(dto.getMail(), dto.getMessageTitle(), newContent, dto.getMessageContentType());
		this.save(dto, isSend, MyCache.MAIL_DTO.getChannelId(), null);
	}
	/**
	 * 非模板消息
	 */
	private void doNotTemplatMessage(MsgMailMessageDto dto){
		boolean isSend = this.send(dto.getMail(), dto.getMessageTitle(), dto.getMessageContent(), dto.getMessageContentType());
		this.save(dto, isSend, MyCache.MAIL_DTO.getChannelId(), null);
	}

	private void save(MsgMailMessageDto dto, boolean isSend, String channelId, String descs) {
		Date date = new Date();

		if(isSend){
			//发送成功
			MsgEmailMessage newMsgEmailMessage = new MsgEmailMessage();
			newMsgEmailMessage.setCreateTime(date);
			newMsgEmailMessage.setUpdateTime(date);
			newMsgEmailMessage.setMsgId(dto.getMsgId());
			newMsgEmailMessage.setUserId(dto.getUserId());
			newMsgEmailMessage.setUserName(dto.getUserName());
			newMsgEmailMessage.setMail(dto.getMail());
			newMsgEmailMessage.setTemplateId(tranLong(dto.getTemplateId()));
			newMsgEmailMessage.setTempleteParams(dto.getTemplateParams());
			newMsgEmailMessage.setMessageTitle(dto.getMessageTitle());
			newMsgEmailMessage.setMessageContent(dto.getMessageContent());
			newMsgEmailMessage.setChannelId(channelId);
			newMsgEmailMessage.setOperatorId(dto.getOperatorId());
			newMsgEmailMessage.setOperatorName(dto.getOperatorName());
			daoFactory.getMsgEmailMessageMapper().insertSelective(newMsgEmailMessage);
		}else{
			// 发送失败
			String content = JUtil.toJson(dto);
			log.error(String.format("channelId:%s发送失败,内容:%s", channelId, content));
			super.saveMsgErrorLog(date, channelId, ChannelTypeEnum.PHONE.getText(), content, descs);
		}
	}

	@Override
	public boolean send(String toMail, String subject, String content, String contentType){
		if("T".equals(contentType)){
			return serviceFactory.getMailService().sendSimpleMail(MyCache.MAIL_DTO, toMail, subject, content);	
		}else{
			return serviceFactory.getMailService().sendHtmlMail(MyCache.MAIL_DTO, toMail, subject, content);
		}
	}
}
