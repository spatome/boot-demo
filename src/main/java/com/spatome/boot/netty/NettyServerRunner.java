package com.spatome.boot.netty;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class NettyServerRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		new NettyServer(9970).start();
	}

}
