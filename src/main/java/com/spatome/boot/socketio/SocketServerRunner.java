package com.spatome.boot.socketio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIOServer;

@Component
@Order(1)
public class SocketServerRunner implements CommandLineRunner {

	@Autowired
	private SocketIOServer socketIOServer;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("==>SocketIo.run...");
		socketIOServer.start();
	}

}
