package com.spatome.boot.socketio;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class task {

	@Scheduled(cron="0/5 * * * * *")
	public void sendTest(){
		SocketEventHandler.sendOne("zw001", "当前时间:"+String.valueOf(System.currentTimeMillis()));
	}
	
}
