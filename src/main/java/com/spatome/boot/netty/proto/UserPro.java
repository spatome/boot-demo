package com.spatome.boot.netty.proto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserPro implements IMessage<UserPro> {

	private long id;
	private String userName;
	private int age;

	@Override
	public void messageHandler(String serverMessageId, String clientMessageId, UserPro message) {
		System.out.println("UserPro:"+message);
	}

}
