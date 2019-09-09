package com.shby.message.oapi.mail.service;

import com.shby.message.oapi.mail.MailDto;

public interface MailService {

	public boolean sendSimpleMail(final MailDto mailDto, String toMail, String subject, String content);
	public boolean sendSimpleMail(final MailDto mailDto, String[] toMail, String subject, String content);
	public boolean sendHtmlMail(final MailDto mailDto, String toMail, String subject, String content);
	public boolean sendHtmlMail(final MailDto mailDto, String[] toMail, String subject, String content);

}
