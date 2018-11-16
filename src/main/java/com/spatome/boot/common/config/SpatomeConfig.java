package com.spatome.boot.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spatome")
public class SpatomeConfig {

	private static String userName;

	public static String getUserName() {
		return userName;
	}

	@Value("userName")
	public void setUserName(String userName) {
		SpatomeConfig.userName = userName;
	}
}
