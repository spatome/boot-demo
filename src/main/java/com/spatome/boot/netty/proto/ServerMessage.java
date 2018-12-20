package com.spatome.boot.netty.proto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerMessage {

	private String messageId;
	private Object messageObj;
	private Class<?> messageObjClass;

	public ServerMessage(String messageId, Object messageObj) {
		this.messageId = messageId;
		this.messageObj = messageObj;
		this.messageObjClass = messageObj.getClass();
	}

}
