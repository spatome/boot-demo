package com.spatome.boot.netty.handler;

import com.spatome.boot.netty.proto.EnterprisePro;
import com.spatome.boot.netty.proto.ServerMessage;

public class EnterpriseProHandler implements IMessage<EnterprisePro> {

	@Override
	public void messageHandler(EnterprisePro message, ServerMessage serverMessage) {
		System.out.println("==>EnterpriseProHandler:"+message);

		serverMessage.setServerMessageId("echo");
		serverMessage.setMessageObj(message);
		serverMessage.setMessageObjClass(EnterprisePro.class);
	}

}
