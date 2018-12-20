package com.spatome.boot.netty;

import com.spatome.boot.netty.bean.MyDecoder;
import com.spatome.boot.netty.bean.MyEncoder;
import com.spatome.boot.netty.proto.ClientMessage;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class NettyServerInitalizer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new MyEncoder());
		pipeline.addLast(new MyDecoder(ClientMessage.class));
		pipeline.addLast(new NettyServerHandler());
	}

}
