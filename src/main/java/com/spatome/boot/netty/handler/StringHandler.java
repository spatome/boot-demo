package com.spatome.boot.netty.handler;

import com.spatome.boot.netty.proto.ClientMessage;

public class StringHandler implements IMessage<String> {

	@Override
	public void messageHandler(String message, ClientMessage clientMessage) {
		System.out.println("==>StringHandler:"+message);
	}

}
