package com.spatome.boot.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyClientHandler extends SimpleChannelInboundHandler<String> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		log.info("==>收到服务端消息:{}", msg);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("==>正在连接...");
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
		log.info("==>连接关闭 ");
	}

}
