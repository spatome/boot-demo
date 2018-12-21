package com.spatome.boot.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 */
@Slf4j
public class NettyServer {

	private final int port;

	public NettyServer(int port) {
		this.port = port;
	}

	public void start() throws Exception{
		EventLoopGroup bossGroup = new NioEventLoopGroup();		//用来接收进来的连接
		EventLoopGroup workerGroup = new NioEventLoopGroup();	//用来处理已经被接收的连接
		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, workerGroup);
			serverBootstrap.channel(NioServerSocketChannel.class);	//这里告诉Channel如何接收新的连接
			serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
			serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
			serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);
			serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
			serverBootstrap.childHandler(new NettyServerInitalizer());
			// 绑定端口,开始接收进来的连接
			ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
			log.info("==>netty服务启动: [port:" + port + "]");
			// 等待服务器socket关闭
			channelFuture.channel().closeFuture().sync();
		}finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

}
