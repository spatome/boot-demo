package com.spatome.boot.netty;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class NettyClientRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		NettyClient.getInstance().start("192.168.0.185", 9970);
	}

}
