package com.spatome.boot.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="spatome")
@Configuration
public class SpatomeConfig {

	private String userName;
	private String zwCount;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getZwCount() {
		return zwCount;
	}

	public void setZwCount(String zwCount) {
		this.zwCount = zwCount;
	}
}
