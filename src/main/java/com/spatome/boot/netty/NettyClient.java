package com.spatome.boot.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyClient {
	private static volatile NettyClient instance;
	private NettyClient(){
	};
    public static NettyClient getInstance() {
        if (instance == null) {
            synchronized (NettyClient.class) {
                if (instance == null) {
                	instance = new NettyClient();
                }
            }
        }
        return instance;
    }


    private ChannelFuture cf;
	public void start(String host, int port) throws Exception{
		EventLoopGroup group = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(group).channel(NioSocketChannel.class);
		bootstrap.option(ChannelOption.TCP_NODELAY, true);
		bootstrap.option(ChannelOption.SO_KEEPALIVE, true);	//两小时内没有数据的通信时，TCP会自动发送一个活动探测数据报文
		bootstrap.handler(new NettyClientInitalizer());
		cf = bootstrap.connect(host, port).sync();
		if(cf.isSuccess()){
			log.info("==>netty客户端连接{}:{}成功", host, port);	
		}else{
			log.info("==>netty客户端连接{}:{}失败", host, port);
		}
	}
	
	public void send(String message){
		if(cf!=null && cf.channel().isActive()){
			cf.channel().writeAndFlush(message);
			System.out.println("==>nettyClient发送消息:"+message);
		}else{
			System.out.println("==>cf不能用");
		}
	}

}
