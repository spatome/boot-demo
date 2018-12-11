package com.spatome.boot.socketio;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SocketEventHandler {
	@Autowired
	private SocketIOServer socketIOServer;

	private static Map<String, Object> socketMap = new HashMap<>();

	@OnConnect
	public void onConnect(SocketIOClient client) {
		System.out.println("==>onConnect");
		String userNo = client.getHandshakeData().getSingleUrlParam("userNo");
		log.info("用户{}上线了, sessionId: {}", userNo, client.getSessionId().toString());
		socketMap.put(userNo, client);
		// notification count
		long count = 10; // 这一步可以通过调用service来查数据库拿到真实数据

		Map<String, Object> map = new HashMap<>();
		map.put("count", count);
		client.sendEvent("notification", map);
	}

	@OnDisconnect
	public void onDisConnect(SocketIOClient client) {
		String[] userNo = new String[1];
		socketMap.forEach((key, value) -> {
			if (value == client) userNo[0] = key;
		});
		log.info("用户{}离开了", userNo[0]);
		socketMap.remove(userNo[0]);
	}

//	// 自定义一个notification事件，也可以自定义其它任何名字的事件
//	@OnEvent("notification")
//	public void notification(SocketIOClient client,	AckRequest ackRequest, Message message) {
//		String topicUserNo = (String)message.getPayload().get("topicUserName");
//		String username = (String) message.getPayload().get("username");
//		String title = (String) message.getPayload().get("title");
//		String titleId = (String) message.getPayload().get("id");
//		String msg = "用户: %s 评论了你的话题: <a href='/topic/%s'>%s</a>";
//		// notification count long count = 10;
//
//		//Map<String, Object> map = new HashMap<>(); map.put("count", count);
//		//map.put("message", String.format(msg, username, titleId, title));
//		if(socketMap.get(topicUserNo) != null)
//			((SocketIOClient)socketMap.get(topicUserNo)).sendEvent("notification", map);
//	}
}
