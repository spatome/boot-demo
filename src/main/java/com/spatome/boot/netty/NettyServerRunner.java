package com.spatome.boot.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.spatome.boot.netty.config.NettyConfig;

@Component
@Order(1)
public class NettyServerRunner implements CommandLineRunner {

	@Autowired
	private NettyConfig nettyConfig;

	@Override
	public void run(String... args) throws Exception {
		new NettyServer(nettyConfig.getPort()).start();
	}

}
