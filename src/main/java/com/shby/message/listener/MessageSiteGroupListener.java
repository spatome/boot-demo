package com.shby.message.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.shby.api.dto.msg.MsgSiteMessageGroupDto;

@Component
@RabbitListener(queues="queue.message.site.group")
public class MessageSiteGroupListener extends BaseListener {

    @RabbitHandler
    public void recieved(MsgSiteMessageGroupDto msg) {
    	serviceFactory.getMessageSiteGroupService().execute(msg);
    }
    
}
