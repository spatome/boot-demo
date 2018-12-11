package com.spatome.boot.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.spatome.boot.netty.config.NettyConfig;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

@Component
@Order(1)
@Slf4j
public class NettyServerRunner implements CommandLineRunner {

	@Autowired
	private NettyConfig nettyConfig;

	@Override
	public void run(String... args) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, workerGroup);
			serverBootstrap.channel(NioServerSocketChannel.class);
			serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
			serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
			serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);
			serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
			serverBootstrap.childHandler(new NettyServerInitalizer());
			// 绑定端口,开始接收进来的连接
			ChannelFuture channelFuture = serverBootstrap.bind(nettyConfig.getPort()).sync();
			log.info("netty服务启动: [port:" + nettyConfig.getPort() + "]");
			// 等待服务器socket关闭
			channelFuture.channel().closeFuture().sync();
		}catch (Exception e) {
			log.error("netty服务启动异常-" + e.getMessage());
		}finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

}
