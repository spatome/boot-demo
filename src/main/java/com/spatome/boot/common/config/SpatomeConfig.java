package com.spatome.boot.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spatome")
public class SpatomeConfig {

	private static String userName;
	private static String password;

	public static String getUserName() {
		return userName;
	}

	@Value("userName")
	public void setUserName(String userName) {
		SpatomeConfig.userName = userName;
	}

	public static String getPassword() {
		return password;
	}

	@Value("password")
	public void setPassword(String password) {
		SpatomeConfig.password = password;
	}
}
