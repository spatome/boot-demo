package com.spatome.boot.socketio;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SocketEventHandler {
	private static Map<String, SocketIOClient> socketMap = new HashMap<String, SocketIOClient>();

	@OnConnect
	public void onConnect(SocketIOClient client) {
		String userNo = client.getHandshakeData().getSingleUrlParam("userNo");
		log.info("用户{}上线了, sessionId: {}", userNo, client.getSessionId().toString());
		socketMap.put(userNo, client);
	}

	@OnDisconnect
	public void onDisConnect(SocketIOClient client) {
		String userNo = client.getHandshakeData().getSingleUrlParam("userNo");
		log.info("用户{}下线了, sessionId: {}", userNo, client.getSessionId().toString());
		socketMap.remove(userNo);
	}

/*	@OnEvent(value = "notify")
	public void onEvent(SocketIOClient client, String data, AckRequest request) {
	    log.info("收到消息:{}", data);
	}*/

	public static void send(String message){
		log.info("群发消息:{}", message);
	}

	public static void sendOne(String userNo, String message){
		log.info("向用户{}发消息:{}", userNo, message);
		SocketIOClient client = socketMap.get(userNo);
		if(client==null){
			log.error("用户{}不在线", userNo);
		}else{
			client.sendEvent("notify", message);
		}
	}
}
