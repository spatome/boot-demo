package com.spatome.boot.netty.proto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientMessage {

	private String clientMessageId;
	private String serverMessageId;
	private Object messageObj;
	private Class<?> messageObjClass;

	public ClientMessage(String clientMessageId, String serverMessageId, Object messageObj, Class<?> messageObjClass) {
		this.clientMessageId = clientMessageId;
		this.serverMessageId = serverMessageId;
		this.messageObj = messageObj;
		this.messageObjClass = messageObjClass;
	}

}
