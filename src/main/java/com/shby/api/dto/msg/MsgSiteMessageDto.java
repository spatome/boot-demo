package com.shby.api.dto.msg;

import lombok.Data;

import java.io.Serializable;

/**
 * dto
 * 站内消息
 *
 */
@Data
public class MsgSiteMessageDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String msgId;

	private Long userId;				//收的人
	private String userName;

	private String messageTitle;
	private String messageContent;

	private Long operatorId;
	private String operatorName;

	//模板
	private String templetId;
	private String templetParams;		//#分割
}
