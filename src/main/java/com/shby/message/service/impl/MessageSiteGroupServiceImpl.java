package com.shby.message.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shby.api.dto.msg.MsgSiteMessageGroupDto;
import com.shby.api.dto.msg.MsgSiteMessageGroupDto.Data;
import com.shby.message.common.constants.MyCache;
import com.shby.message.common.enums.ChannelTypeEnum;
import com.shby.message.entity.MsgBatchSiteMessage;
import com.shby.message.entity.MsgSiteMessage;
import com.shby.message.service.BaseService;
import com.shby.message.service.MessageSiteGroupService;
import com.shby.message.util.SUtil;
import com.shby.message.util.convert.JUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageSiteGroupServiceImpl extends BaseService implements MessageSiteGroupService {

	@Transactional
	@Override
	public void execute(MsgSiteMessageGroupDto dto) {
		log.info("《==" + dto);
		if (dto.getDataList().size()>500) {
			this.save(dto, false, "超过数量500限制");
			return;
		}

		if (StringUtils.isNotBlank(dto.getTemplateId())) {
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
	private void doTemplatMessage(MsgSiteMessageGroupDto dto) {
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
	private void doNotTemplatMessage(MsgSiteMessageGroupDto dto) {
		this.save(dto, true, null);
	}

	private void save(MsgSiteMessageGroupDto dto, boolean isSend, String descs) {
		Date date = new Date();

		StringBuffer userIds = new StringBuffer("");
		StringBuffer userNames = new StringBuffer("");
		for (Data data : dto.getDataList()) {
			if(data.getUserId()!=null) userIds.append(data.getUserId()+",");
			if(data.getUserName()!=null) userNames.append(data.getUserName()).append(",");
		}
		MsgBatchSiteMessage newMsgBatchSiteMessage = new MsgBatchSiteMessage();
		newMsgBatchSiteMessage.setCreateTime(date);
		newMsgBatchSiteMessage.setUpdateTime(date);
		newMsgBatchSiteMessage.setMsgId(dto.getMsgId());
		newMsgBatchSiteMessage.setUserIds(userIds.toString());
		newMsgBatchSiteMessage.setUserNames(userNames.toString());
		newMsgBatchSiteMessage.setTemplateId(tranLong(dto.getTemplateId()));
		newMsgBatchSiteMessage.setTempleteParams(dto.getTemplateParams());
		newMsgBatchSiteMessage.setMessageTitle(dto.getMessageTitle());
		newMsgBatchSiteMessage.setMessageContent(dto.getMessageContent());
		newMsgBatchSiteMessage.setOperatorId(dto.getOperatorId());
		newMsgBatchSiteMessage.setOperatorName(dto.getOperatorName());
		daoFactory.getMsgBatchSiteMessageMapper().insertSelective(newMsgBatchSiteMessage);

		if(isSend){
			//发送成功
			List<MsgSiteMessage> msgSiteMessageList = new ArrayList<MsgSiteMessage>();
			for (Data data : dto.getDataList()) {
				MsgSiteMessage newRecord = new MsgSiteMessage();
				newRecord.setCreateTime(date);
				newRecord.setUpdateTime(date);
				newRecord.setMsgId(dto.getMsgId());
				newRecord.setUserId(data.getUserId());
				newRecord.setUserName(data.getUserName());
				newRecord.setTemplateId(tranLong(dto.getTemplateId()));
				newRecord.setTempleteParams(dto.getTemplateParams());
				newRecord.setMessageTitle(dto.getMessageTitle());
				newRecord.setMessageContent(dto.getMessageContent());
				newRecord.setOperatorId(dto.getOperatorId());
				newRecord.setOperatorName(dto.getOperatorName());
				newRecord.setIsBatch((byte)1);
				msgSiteMessageList.add(newRecord);
			}
			if (msgSiteMessageList.size() > 0) {
				daoFactory.getMsgSiteMessageMapper().batchInsert(msgSiteMessageList);
			}		
		}else{
			// 发送失败
			String content = JUtil.toJson(dto);
			log.error(String.format("channelId:%s发送失败,内容:%s", "", content));
			super.saveMsgErrorLog(date, null, ChannelTypeEnum.SITE.name(), content, descs);
		}
	}
	
	@Override
	public boolean send(String to, String title, String content) {
		return true;
	}

}
