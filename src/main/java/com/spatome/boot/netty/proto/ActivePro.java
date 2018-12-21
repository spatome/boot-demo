package com.spatome.boot.netty.proto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 报文
 * channel激活
 */
@Getter
@Setter
@ToString
public class ActivePro implements IMessage<ActivePro> {

	private String value;
	
	@Override
	public void messageHandler(String serverMessageId, String clientMessageId, ActivePro message) {
		System.out.println("StringPro:"+message);
	}

}
