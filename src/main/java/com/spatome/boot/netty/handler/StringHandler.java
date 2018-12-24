package com.spatome.boot.netty.handler;

import com.spatome.boot.netty.proto.ServerMessage;

public class StringHandler implements IMessage<String> {

	@Override
	public void messageHandler(String message, ServerMessage serverMessage) {
		
		System.out.println("==>StringHandler:"+message);

		serverMessage.setServerMessageId("echo");
		serverMessage.setMessageObj(message);
		serverMessage.setMessageObjClass(String.class);
	}

}
