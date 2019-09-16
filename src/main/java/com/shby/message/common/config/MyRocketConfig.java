package com.shby.message.common.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

@SpringBootConfiguration
@Slf4j
public class MyRocketConfig {

    @Value("${rocketmq.producer.name-server}")
    private String nameServer;
    @Value("${rocketmq.producer.group}")
    private String group;

    @Bean
    public DefaultMQProducer getRocketMQProducer() {
        if (StringUtils.isEmpty(this.group)) {
            throw new RuntimeException("group null");
        }
        if (StringUtils.isEmpty(this.nameServer)) {
        	throw new RuntimeException("nameServer null");
        }

        DefaultMQProducer producer = new DefaultMQProducer(group);
        producer.setNamesrvAddr(this.nameServer);

        try {
            producer.start();

            log.info(String.format("== rocket producer is start ! groupName:[%s],namesrvAddr:[%s]"
                    , this.group, this.nameServer));
        } catch (MQClientException e) {
            log.error(String.format("producer is error {}", e.getMessage(),e));
            throw new RuntimeException(e);
        }

        return producer;
    }
}
