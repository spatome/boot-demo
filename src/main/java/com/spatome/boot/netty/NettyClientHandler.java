package com.spatome.boot.netty;

import com.spatome.boot.netty.proto.ServerMessage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyClientHandler extends SimpleChannelInboundHandler<ServerMessage> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ServerMessage msg) throws Exception {
		try {
			log.info("==>收到服务端消息:{}", msg.getMessageObj());
		} finally {
			ReferenceCountUtil.release(msg);
		}
		
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
