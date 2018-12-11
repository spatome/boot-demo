//package com.spatome.boot.socketio;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@WebListener
//public class SocketioLisener implements ServletContextListener {
//
//	@Override
//	public void contextInitialized(ServletContextEvent sce) {
//        //启动Socketio服务
//        SocketioUtil socketioUtil = new SocketioUtil();
//        socketioUtil.startServer();
//	}
//
//	@Override
//	public void contextDestroyed(ServletContextEvent sce) {
//        //关闭Socketio服务
//		SocketioUtil socketioUtil = new SocketioUtil();
//		socketioUtil.stop();
//	}
//
//}
