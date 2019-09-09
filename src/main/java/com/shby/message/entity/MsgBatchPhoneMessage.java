package com.shby.message.entity;

import java.util.Date;

public class MsgBatchPhoneMessage {
    private Long id;

    private String msgId;

    private String userIds;

    private String userNames;

    private String phoneNums;

    private Long templateId;

    private String templeteParams;

    private String messageTitle;

    private String messageContent;

    private String channelId;

    private Short msgStatus;

    private String sendIp;

    private Long operatorId;

    private String operatorName;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId == null ? null : msgId.trim();
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds == null ? null : userIds.trim();
    }

    public String getUserNames() {
        return userNames;
    }

    public void setUserNames(String userNames) {
        this.userNames = userNames == null ? null : userNames.trim();
    }

    public String getPhoneNums() {
        return phoneNums;
    }

    public void setPhoneNums(String phoneNums) {
        this.phoneNums = phoneNums == null ? null : phoneNums.trim();
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getTempleteParams() {
		return templeteParams;
	}

	public void setTempleteParams(String templeteParams) {
		this.templeteParams = templeteParams;
	}

	public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle == null ? null : messageTitle.trim();
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public Short getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(Short msgStatus) {
        this.msgStatus = msgStatus;
    }

    public String getSendIp() {
        return sendIp;
    }

    public void setSendIp(String sendIp) {
        this.sendIp = sendIp == null ? null : sendIp.trim();
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}