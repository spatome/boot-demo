package com.shby.message.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.shby.api.dto.msg.MsgMailMessageDto;

@Component
@RabbitListener(queues="queue.message.mail")
public class MessageMailListener extends BaseListener {

    @RabbitHandler
    public void recieved(MsgMailMessageDto msg) {
    	serviceFactory.getMessageMailService().execute(msg);
    }
    
}
