package com.shby.message.factory;

import com.shby.message.oapi.mail.service.MailService;
import com.shby.message.oapi.smsyp.service.SmsBaseService;
import com.shby.message.service.MessageMailGroupService;
import com.shby.message.service.MessageMailService;
import com.shby.message.service.MessagePhoneGroupService;
import com.shby.message.service.MessagePhoneService;
import com.shby.message.service.MessageSiteGroupService;
import com.shby.message.service.MessageSiteService;

public interface ServiceFactory {

	public MessagePhoneService getMessagePhoneService();
	public MessagePhoneGroupService getMessagePhoneGroupService();
	public MessageMailService getMessageMailService();
	public MessageMailGroupService getMessageMailGroupService();
	public MessageSiteService getMessageSiteService();
	public MessageSiteGroupService getMessageSiteGroupService();

	//第三方接口
	public SmsBaseService getSmsBaseService();		//短信接口
	public MailService getMailService();
}
