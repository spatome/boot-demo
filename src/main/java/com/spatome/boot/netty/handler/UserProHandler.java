package com.spatome.boot.netty.handler;

import com.spatome.boot.netty.proto.ClientMessage;
import com.spatome.boot.netty.proto.UserPro;

public class UserProHandler implements IMessage<UserPro> {

	@Override
	public void messageHandler(UserPro message, ClientMessage clientMessage) {
		System.out.println("==>UserProHandler:"+message);
	}

}
