package com.shby.message.oapi.mail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailDto {

	private boolean isOn;

	private String channelId;
	private String mailHost;
	private String mailPort;
	private String mailPwd;
	private String userName;
	private String nickName;
	private int intervalSec = 0;

}
