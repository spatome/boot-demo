package com.spatome.boot.netty.proto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ServerMessage {

	private String serverMessageId;
	private String clientMessageId;
	private Object messageObj;
	private Class<?> messageObjClass;

	public ServerMessage(String serverMessageId, String clientMessageId, Object messageObj, Class<?> messageObjClass) {
		this.serverMessageId = serverMessageId;
		this.clientMessageId = clientMessageId;
		this.messageObj = messageObj;
		this.messageObjClass = messageObjClass;
	}
}
