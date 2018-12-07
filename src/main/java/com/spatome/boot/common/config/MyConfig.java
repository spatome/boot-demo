package com.spatome.boot.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="my")
@Configuration
public class MyConfig {

	public static String userName;
	private String zwCount;

	@Value("${my.userName:z}")
	public void setUserName(String userName) {
		MyConfig.userName = userName;
	}

	public String getZwCount() {
		return zwCount;
	}

	public void setZwCount(String zwCount) {
		this.zwCount = zwCount;
	}
}
