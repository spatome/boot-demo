package com.shby.message.oapi.smsyp.service;

import com.shby.message.oapi.smsyp.SmsDto;
import com.shby.message.oapi.smsyp.SmsSendTypeEnum;

public interface SmsBaseService {

	public boolean sendMessage(final SmsDto smdDto, SmsSendTypeEnum type, String phone, String content);

	/**
	 * 释放短信网关相关的资源
	 */
	public void release();
	
}
