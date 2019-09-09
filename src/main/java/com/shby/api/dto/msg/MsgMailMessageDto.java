package com.shby.api.dto.msg;

import lombok.Data;

import java.io.Serializable;

/**
 * dto
 * 邮箱消息
 *
 */
@Data
public class MsgMailMessageDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String msgId;

	private Long userId;				//收的人
	private String userName;

	private String mail;				//目标邮箱

	private String messageTitle;
	private String messageContent;
	private String messageContentType;	//T(txt) H(html)

	private Long operatorId;
	private String operatorName;

	//模板
	private String templetId;
	private String templetParams;		//#分割
}
