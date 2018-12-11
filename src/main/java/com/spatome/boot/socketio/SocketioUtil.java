//package com.spatome.boot.socketio;
//
//import java.util.Collection;
//
//import com.corundumstudio.socketio.Configuration;
//import com.corundumstudio.socketio.SocketIOClient;
//import com.corundumstudio.socketio.SocketIOServer;
//import com.corundumstudio.socketio.listener.ConnectListener;
//import com.spatome.boot.socketio.config.SocketioConfig;
//
///**
// * 创建 添加 启动客户端 关闭服务 消息推送
// */
//public class SocketioUtil {
//
//	private static SocketIOServer socketIOServer;
//
//	public void start() {
//		Configuration conf = new Configuration();
//		conf.setHostname(SocketioConfig.host);
//		conf.setPort(SocketioConfig.port);
//		// 设置最大的WebSocket帧内容长度限制
//		conf.setMaxFramePayloadLength(1024 * 1024);
//		// 设置最大HTTP内容长度限制
//		conf.setMaxHttpContentLength(1024 * 1024);
//		socketIOServer = new SocketIOServer(conf);
//
//		ConnectListener connect = new ConnectListener() {
//			@Override
//			public void onConnect(SocketIOClient client) {
//
//			}
//		};
//		// 添加客户端
//		socketIOServer.addConnectListener(connect);
//		socketIOServer.start();
//	}
//
//	/**
//	 * @Title: push
//	 * @Description: 全体消息推送
//	 * @param type
//	 * 				根据类型接收消息
//	 * @param content
//	 *            推送的内容
//	 */
//	public void push(String type, Object content) {
//		// 获取全部客户端
//		Collection<SocketIOClient> allClients = socketIOServer.getAllClients();
//		for (SocketIOClient socket : allClients) {
//			System.out.println("==>"+content);
//			socket.sendEvent(type, content);
//		}
//	}
//
//	/**
//	 * @Description: 启动服务
//	 */
//	public void startServer() {
//		if (socketIOServer == null) {
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					start();
//				}
//			}).start();
//		}
//	}
//
//	public void stop() {
//		if (socketIOServer != null) {
//			socketIOServer.stop();
//			socketIOServer = null;
//		}
//	}
//}
