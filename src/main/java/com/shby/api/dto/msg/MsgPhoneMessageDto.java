package com.shby.api.dto.msg;

import lombok.Data;

import java.io.Serializable;

/**
 * dto
 * 短信消息
 *
 */
@Data
public class MsgPhoneMessageDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String msgId;

	private Long userId;				//收的人
	private String userName;
	private String phoneNum;			//目标电话

	private String messageTitle;
	private String messageContent;

	private Long operatorId;
	private String operatorName;

	//模板
	private String templateId;
	private String templateParams;		//#分割
}
