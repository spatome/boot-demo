package com.shby.message.initiate;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.shby.message.common.constants.MyCache;
import com.shby.message.common.enums.ChannelTypeEnum;
import com.shby.message.entity.MsgEmailChannel;
import com.shby.message.entity.MsgPhoneChannel;
import com.shby.message.entity.MsgTemplate;
import com.shby.message.service.BaseService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ConfigCommandLineRunner extends BaseService implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		this.cacheSmsChannelId();
		this.cacheMailChannelId();

		this.cacheSmsTemplate();
		this.cacheMailTemplate();
		this.cacheSiteTemplate();
	}

	/**
	 * 短信信道
	 */
	private void cacheSmsChannelId(){
		MsgPhoneChannel msgPhoneChannelQ = new MsgPhoneChannel();
		msgPhoneChannelQ.setStatus("ON");
		List<MsgPhoneChannel> phoneChannelList = daoFactory.getMsgPhoneChannelMapper().selectByBean(msgPhoneChannelQ);
		MsgPhoneChannel cc = null;
		if(phoneChannelList.size()==0){
			log.error("============未设置短信通道============");
			MyCache.SMS_DTO.setOn(false);
			return;
		}else if(phoneChannelList.size()>1){
			log.warn("============重复设置短信通道============");
			cc = phoneChannelList.get(0);
		}else{
			log.info("============设置短信通道 OK============");
			cc = phoneChannelList.get(0);
		}
		MyCache.SMS_DTO.setOn(true);
		MyCache.SMS_DTO.setChannelId(cc.getChannelId());
		MyCache.SMS_DTO.setSmsportUrl(cc.getPortUrl());
		MyCache.SMS_DTO.setSmsportPwdSystem(cc.getSystemSignKey());
		MyCache.SMS_DTO.setSmsportPwd(cc.getSignKey());
	}

	/**
	 * 邮箱信道
	 */
	private void cacheMailChannelId(){
		MsgEmailChannel msgEmailChannelQ = new MsgEmailChannel();
		msgEmailChannelQ.setStatus("ON");
		List<MsgEmailChannel> msgEmailChannelList = daoFactory.getMsgEmailChannelMapper().selectByBean(msgEmailChannelQ);
		MsgEmailChannel cc = null;
		if(msgEmailChannelList.size()==0){
			log.error("============未设置邮箱通道============");
			MyCache.MAIL_DTO.setOn(false);
			return;
		}else if(msgEmailChannelList.size()>1){
			log.warn("============重复设置邮箱通道============");
			cc = msgEmailChannelList.get(0);
		}else{
			log.info("============设置邮箱通道 OK============");
			cc = msgEmailChannelList.get(0);
		}
		MyCache.MAIL_DTO.setOn(true);
		MyCache.MAIL_DTO.setChannelId(cc.getChannelId());
		MyCache.MAIL_DTO.setMailHost(cc.getHost());
		MyCache.MAIL_DTO.setMailPort(cc.getPort());
		MyCache.MAIL_DTO.setMailPwd(cc.getPassword());
		MyCache.MAIL_DTO.setUserName(cc.getNickName());
		MyCache.MAIL_DTO.setIntervalSec(cc.getIntervalSec());
	}

	/**
	 * 短信模板
	 */
	private void cacheSmsTemplate(){
		MsgTemplate msgTemplateQ = new MsgTemplate();
		msgTemplateQ.setStatus("ON");
		msgTemplateQ.setChannelType(ChannelTypeEnum.PHONE.name());
		List<MsgTemplate> msgTemplateList = daoFactory.getMsgTemplateMapper().selectByBean(msgTemplateQ);
		for (MsgTemplate msgTemplate : msgTemplateList) {
			MyCache.SMS_TEMPLATE_MAP.put(msgTemplate.getTemplateNo(), msgTemplate.getTemplateContent());
		}
	}
	/**
	 * 邮件模板
	 */
	private void cacheMailTemplate(){
		MsgTemplate msgTemplateQ = new MsgTemplate();
		msgTemplateQ.setStatus("ON");
		msgTemplateQ.setChannelType(ChannelTypeEnum.MAIL.name());
		List<MsgTemplate> msgTemplateList = daoFactory.getMsgTemplateMapper().selectByBean(msgTemplateQ);
		for (MsgTemplate msgTemplate : msgTemplateList) {
			MyCache.MAIL_TEMPLATE_MAP.put(msgTemplate.getTemplateNo(), msgTemplate.getTemplateContent());
		}
	}

	/**
	 * 站内模板
	 */
	private void cacheSiteTemplate(){
		MsgTemplate msgTemplateQ = new MsgTemplate();
		msgTemplateQ.setStatus("ON");
		msgTemplateQ.setChannelType(ChannelTypeEnum.SITE.name());
		List<MsgTemplate> msgTemplateList = daoFactory.getMsgTemplateMapper().selectByBean(msgTemplateQ);
		for (MsgTemplate msgTemplate : msgTemplateList) {
			MyCache.SITE_TEMPLATE_MAP.put(msgTemplate.getTemplateNo(), msgTemplate.getTemplateContent());
		}
	}
}
