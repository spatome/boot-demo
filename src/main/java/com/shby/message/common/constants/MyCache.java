package com.shby.message.common.constants;

import java.util.HashMap;
import java.util.Map;

import com.shby.message.oapi.mail.MailDto;
import com.shby.message.oapi.smsyp.SmsDto;

/**
 * 初始化的静态数据
 */
public class MyCache {

	/**
	 * 短信通道
	 */
	public final static SmsDto SMS_DTO = new SmsDto();
	/**
	 * 邮箱通道
	 */
	public final static MailDto MAIL_DTO = new MailDto();

	/**
	 * 短信模板
	 */
	public final static Map<String, String> SMS_TEMPLATE_MAP = new HashMap<String, String>();
	/**
	 * 邮件模板
	 */
	public final static Map<String, String> MAIL_TEMPLATE_MAP = new HashMap<String, String>();
	/**
	 * 站内模板
	 */
	public final static Map<String, String> SITE_TEMPLATE_MAP = new HashMap<String, String>();
}
