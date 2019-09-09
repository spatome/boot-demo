package com.shby.message.factory.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.shby.message.factory.ServiceFactory;
import com.shby.message.oapi.mail.service.MailService;
import com.shby.message.oapi.smsyp.service.SmsBaseService;
import com.shby.message.service.MessageMailGroupService;
import com.shby.message.service.MessageMailService;
import com.shby.message.service.MessagePhoneGroupService;
import com.shby.message.service.MessagePhoneService;
import com.shby.message.service.MessageSiteGroupService;
import com.shby.message.service.MessageSiteService;

@Lazy
@Service
public class ServiceFactoryImpl implements ServiceFactory {

	@Autowired
	private MessagePhoneService messagePhoneServiceImpl;
	@Autowired
	private MessagePhoneGroupService messagePhoneGroupServiceImpl;
	@Autowired
	private MessageMailService messageMailServiceImpl;
	@Autowired
	private MessageMailGroupService messageMailGroupServiceImpl;
	@Autowired
	private MessageSiteService messageSiteServiceImpl;
	@Autowired
	private MessageSiteGroupService messageSiteGroupServiceImpl;

	//第三方
	@Autowired
	private SmsBaseService smsYPServiceImpl;
	@Autowired
	private MailService mailServiceImpl;

	@Override
	public MessagePhoneService getMessagePhoneService() {
		return messagePhoneServiceImpl;
	}

	@Override
	public SmsBaseService getSmsBaseService() {
		return smsYPServiceImpl;
	}

	@Override
	public MessagePhoneGroupService getMessagePhoneGroupService() {
		return messagePhoneGroupServiceImpl;
	}

	@Override
	public MessageMailService getMessageMailService() {
		return messageMailServiceImpl;
	}

	@Override
	public MessageMailGroupService getMessageMailGroupService() {
		return messageMailGroupServiceImpl;
	}

	@Override
	public MailService getMailService() {
		return mailServiceImpl;
	}

	@Override
	public MessageSiteService getMessageSiteService() {
		return messageSiteServiceImpl;
	}

	@Override
	public MessageSiteGroupService getMessageSiteGroupService() {
		return messageSiteGroupServiceImpl;
	}
}
