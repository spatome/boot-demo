package com.spatome.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.spatome.boot.socketio.config.SocketioConfig;

@SpringBootApplication
@EnableConfigurationProperties
public class BootApplication {

	@Autowired
	private SocketioConfig socketioConfig;

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

	@Bean
	public SocketIOServer socketIOServer() {
		Configuration conf = new Configuration();
		conf.setHostname(socketioConfig.getHost());
		conf.setPort(socketioConfig.getPort());
	    // 协议升级超时时间（毫秒），默认10000。HTTP握手升级为ws协议超时时间
		conf.setUpgradeTimeout(10000);
	    // Ping消息间隔（毫秒），默认25000。客户端向服务器发送一条心跳消息间隔
		conf.setPingInterval(25000);
	    // Ping消息超时时间（毫秒），默认60000，这个时间间隔内没有接收到心跳消息就会发送超时事件
		conf.setPingTimeout(60000);
	    // 握手协议参数使用JWT的Token认证方案
//	    config.setAuthorizationListener(data -> {
	      // 可以使用如下代码获取用户密码信息
//	      String token = data.getSingleUrlParam("token");
//	      return true;
//	    });

	    return new SocketIOServer(conf);
	  }

	  @Bean
	  public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
	    return new SpringAnnotationScanner(socketServer);
	  }
}
