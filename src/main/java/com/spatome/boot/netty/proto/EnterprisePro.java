package com.spatome.boot.netty.proto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EnterprisePro implements IMessage<EnterprisePro> {

	private long id;
	private String enterpriseNo;
	private String enterpriseName;
	private String address;

	@Override
	public void messageHandler(String serverMessageId, String clientMessageId, EnterprisePro message) {
		System.out.println("EnterprisePro:"+message);
	}
}
