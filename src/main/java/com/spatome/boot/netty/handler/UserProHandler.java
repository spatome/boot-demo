package com.spatome.boot.netty.handler;

import com.spatome.boot.netty.proto.ServerMessage;
import com.spatome.boot.netty.proto.UserPro;

public class UserProHandler implements IMessage<UserPro> {

	@Override
	public void messageHandler(UserPro message, ServerMessage serverMessage) {
		System.out.println("==>UserProHandler:"+message);

		serverMessage.setServerMessageId("echo");
		serverMessage.setMessageObj(message);
		serverMessage.setMessageObjClass(UserPro.class);
	}

}
