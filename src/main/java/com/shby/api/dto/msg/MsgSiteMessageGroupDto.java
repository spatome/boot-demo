package com.shby.api.dto.msg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * dto
 * 站内消息
 */
public class MsgSiteMessageGroupDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String msgId;

	private List<Data> dataList;

	private String messageTitle;
	private String messageContent;

	private Long operatorId;
	private String operatorName;

	// 模板
	private String templetId;
	private String templetParams; // #分割

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public List<Data> getDataList() {
		if (dataList == null) {
			dataList = new ArrayList<Data>();
		}
		return dataList;
	}

	public void setDataList(List<Data> dataList) {
		this.dataList = dataList;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getTempletId() {
		return templetId;
	}

	public void setTempletId(String templetId) {
		this.templetId = templetId;
	}

	public String getTempletParams() {
		return templetParams;
	}

	public void setTempletParams(String templetParams) {
		this.templetParams = templetParams;
	}

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	@Getter
	@Setter
	public static class Data implements Serializable {
		private static final long serialVersionUID = 1L;

		private Long userId;
		private String userName;
	}
}
