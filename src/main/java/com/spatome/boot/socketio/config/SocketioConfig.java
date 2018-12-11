package com.spatome.boot.socketio.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "my.socketio")
@Data
public class SocketioConfig {

	public String host;
	public int port;

}
