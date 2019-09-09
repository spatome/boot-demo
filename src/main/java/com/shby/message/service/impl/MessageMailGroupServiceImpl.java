package com.shby.message.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.shby.api.dto.msg.MsgMailMessageGroupDto;
import com.shby.api.dto.msg.MsgMailMessageGroupDto.Data;
import com.shby.message.common.constants.MyCache;
import com.shby.message.common.enums.ChannelTypeEnum;
import com.shby.message.entity.MsgEmailMessage;
import com.shby.message.service.BaseService;
import com.shby.message.service.MessageMailGroupService;
import com.shby.message.util.SUtil;
import com.shby.message.util.convert.JUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 群发邮件
 */
@Service
@Slf4j
public class MessageMailGroupServiceImpl extends BaseService implements MessageMailGroupService {

	@Override
	public void execute(MsgMailMessageGroupDto dto) {
		log.info("《=="+dto);

		if (dto.getDataList().size()>500) {
			this.save(dto, false, MyCache.MAIL_DTO.getChannelId(), "超过数量500限制");
			return;
		}

		if(StringUtils.isNotBlank(dto.getTempletId())){
			//模板消息
			this.doTemplatMessage(dto);
		}else{
			this.doNotTemplatMessage(dto);
		}
	}

	/**
	 * 模板消息
	 */
	private void doTemplatMessage(MsgMailMessageGroupDto dto){
		String content = MyCache.MAIL_TEMPLATE_MAP.get(dto.getTempletId());
		if(StringUtils.isBlank(content)){
			this.save(dto, false, MyCache.MAIL_DTO.getChannelId(), "模板不存在");
			return;
		}
		String newContent = SUtil.updateContent(content, dto.getTempletParams());
		boolean isSend = this.send(this.getMails(dto.getDataList()), dto.getMessageTitle(), newContent, ChannelTypeEnum.MAIL.getText());
		this.save(dto, isSend, MyCache.MAIL_DTO.getChannelId(), null);
	}
	/**
	 * 非模板消息
	 */
	private void doNotTemplatMessage(MsgMailMessageGroupDto dto){
		boolean isSend = this.send(this.getMails(dto.getDataList()), dto.getMessageTitle(), dto.getMessageContent(), ChannelTypeEnum.MAIL.getText());
		this.save(dto, isSend, MyCache.MAIL_DTO.getChannelId(), null);
	}

	private void save(MsgMailMessageGroupDto dto, boolean isSend, String channelId, String descs) {
		Date date = new Date();

		if(isSend){
			//发送成功
			List<MsgEmailMessage> msgEmailMessageList = new ArrayList<MsgEmailMessage>();
			for (Data data : dto.getDataList()) {
				MsgEmailMessage newMsgEmailMessage = new MsgEmailMessage();
				newMsgEmailMessage.setCreateTime(date);
				newMsgEmailMessage.setUpdateTime(date);
				newMsgEmailMessage.setMsgId(dto.getMsgId());
				newMsgEmailMessage.setUserId(data.getUserId());
				newMsgEmailMessage.setUserName(data.getUserName());
				newMsgEmailMessage.setMail(data.getMail());
				newMsgEmailMessage.setTemplateId(StringUtils.isBlank(dto.getTempletId()) ? null
						: Long.valueOf(dto.getTempletId()));
				newMsgEmailMessage.setTempletParams(dto.getTempletParams());
				newMsgEmailMessage.setMessageTitle(dto.getMessageTitle());
				newMsgEmailMessage.setMessageContent(dto.getMessageContent());
				newMsgEmailMessage.setChannelId(channelId);
				newMsgEmailMessage.setOperatorId(dto.getOperatorId());
				newMsgEmailMessage.setOperatorName(dto.getOperatorName());
				msgEmailMessageList.add(newMsgEmailMessage);
			}
			if (msgEmailMessageList.size() > 0) {
				daoFactory.getMsgEmailMessageMapper().batchInsert(msgEmailMessageList);
			}
		}else{
			// 发送失败
			String content = JUtil.toJson(dto);
			log.error(String.format("channelId:%s发送失败,内容:%s", channelId, content));
			super.saveMsgErrorLog(date, channelId, ChannelTypeEnum.MAIL.name(), content, descs);
		}
	}

	private String[] getMails(List<Data> dataList){
		List<String> result = new ArrayList<String>();

		for (Data data : dataList) {
			result.add(data.getMail());
		}

		return result.toArray(new String[]{});
	}

	@Override
	public boolean send(String[] toMails, String subject, String content, String contentType){
		if("T".equals(contentType)){
			return serviceFactory.getMailService().sendHtmlMail(MyCache.MAIL_DTO, toMails, subject, content);	
		}else{
			return serviceFactory.getMailService().sendHtmlMail(MyCache.MAIL_DTO, toMails, subject, content);
		}
	}
}
