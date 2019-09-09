package com.shby.message.oapi.smsyp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsDto {

	private boolean isOn;
	
	private String channelId;
	private String smsportUrl;
	private String smsportPwd;
	private String smsportPwdSystem;

}
