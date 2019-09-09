package com.shby.message;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;

import com.shby.message.common.constants.MyCache;

@SpringBootApplication
@MapperScan("com.shby.message.dao")
@ComponentScan("com.shby")
@EnableConfigurationProperties
@EnableAsync
public class BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

    @Bean
    public JavaMailSenderImpl JavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(MyCache.MAIL_DTO.getMailHost());
        mailSender.setUsername(MyCache.MAIL_DTO.getUserName());
        mailSender.setPassword(MyCache.MAIL_DTO.getMailPwd());
        return  mailSender;
    }
}
