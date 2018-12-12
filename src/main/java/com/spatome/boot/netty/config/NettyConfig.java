package com.spatome.boot.netty.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "my.netty")
public class NettyConfig {

	private static String host;
	private static int port;

	public static String getHost() {
		return NettyConfig.host;
	}

	@Value("${my.netty.host}")
	public void setHost(String host) {
		NettyConfig.host = host;
	}

	public static int getPort() {
		return NettyConfig.port;
	}

	@Value("${my.netty.port}")
	public void setPort(int port) {
		NettyConfig.port = port;
	}

}
