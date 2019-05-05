package com.spatome.boot.listener;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfirmCallBackListener implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

	@Autowired
	protected RabbitTemplate rabbitTemplate;

	@PostConstruct
	public void init() {
		rabbitTemplate.setConfirmCallback(this);
		rabbitTemplate.setReturnCallback(this);
	}

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		//System.out.println("==============correlationData:"+correlationData);
		System.out.println("==>ack:"+ack);
		//System.out.println("==============cause:"+cause);
	}

	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		System.out.println("==============replyCode:"+replyCode);
		System.out.println("==============message:"+message);
		System.out.println("==============replyText:"+replyText);
		System.out.println("==============exchange:"+exchange);
		System.out.println("==============routingKey:"+routingKey);
	}

}
