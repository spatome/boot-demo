package com.spatome.boot.common.config.memcached;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties("memcached")
@Data
public class MemcachedConfig {

	private String ip;
	private int port;
	
}
