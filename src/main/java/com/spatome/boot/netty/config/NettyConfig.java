package com.spatome.boot.netty.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix="my.netty")
@Data
public class NettyConfig {

	private int port;

}
