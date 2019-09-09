package com.shby.message.oapi.mail.service.impl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.shby.message.oapi.mail.MailDto;
import com.shby.message.oapi.mail.service.MailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender javaMailSenderImpl;

	@Override
	public boolean sendSimpleMail(MailDto mailDto, String toMail, String subject, String content) {
		return sendHtmlMail(mailDto, new String[]{toMail}, subject, content);
	}

	@Override
	public boolean sendHtmlMail(MailDto mailDto, String toMail, String subject, String content) {
		return sendHtmlMail(mailDto, new String[]{toMail}, subject, content);
	}

	@Override
	public boolean sendSimpleMail(MailDto mailDto, String[] toMail, String subject, String content) {
		boolean result = false;

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(mailDto.getUserName());
		simpleMailMessage.setTo(toMail);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(content);
		try {
			javaMailSenderImpl.send(simpleMailMessage);
			result = true;
		} catch (MailException e) {
			log.error("发送"+subject+"失败", e);
		}

		return result;
	}

	@Override
	public boolean sendHtmlMail(MailDto mailDto, String[] toMail, String subject, String content) {
		boolean result = false;

		try {
			MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(mailDto.getUserName());
			mimeMessageHelper.setTo(toMail);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(content, true);
			javaMailSenderImpl.send(mimeMessage);
			result = true;
		} catch (Exception e) {
			log.error("发送"+subject+"失败", e);
		}

		return result;
	}

}
