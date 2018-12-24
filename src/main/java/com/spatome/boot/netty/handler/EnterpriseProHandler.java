package com.spatome.boot.netty.handler;

import com.spatome.boot.netty.proto.ClientMessage;
import com.spatome.boot.netty.proto.EnterprisePro;

public class EnterpriseProHandler implements IMessage<EnterprisePro> {

	@Override
	public void messageHandler(EnterprisePro message, ClientMessage clientMessage) {
		System.out.println("==>EnterpriseProHandler:"+message);
	}

}
