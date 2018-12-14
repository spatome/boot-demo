package com.spatome.boot.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix="my")
@Configuration
@Getter
@Setter
public class MyConfig {

	private String wxAppId;
	private String wxAppSecret;

}
