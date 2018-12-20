package com.spatome.boot.netty.proto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientMessage {

	private String messageId;
	private Object messageObj;
	private Class<?> messageObjClass;

	public ClientMessage(String messageId, Object messageObj) {
		this.messageId = messageId;
		this.messageObj = messageObj;
		this.messageObjClass = messageObj.getClass();
	}
}
