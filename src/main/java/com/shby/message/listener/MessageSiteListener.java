package com.shby.message.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.shby.api.dto.msg.MsgSiteMessageDto;

@Component
@RabbitListener(queues="queue.message.site")
public class MessageSiteListener extends BaseListener {

    @RabbitHandler
    public void recieved(MsgSiteMessageDto msg) {
    	serviceFactory.getMessageSiteService().execute(msg);
    }
    
}
