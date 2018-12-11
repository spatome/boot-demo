package com.spatome.boot.netty;

import java.net.InetAddress;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		String clientId = ctx.channel().id().asLongText();
		log.info("收到clientId:{},消息:{}", clientId, msg);

		ctx.channel().writeAndFlush(msg);
	}

	@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("RamoteAddress:{} active!", ctx.channel().remoteAddress());
        ctx.channel().writeAndFlush( "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");

        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("\nChannel is disconnected");
        super.channelInactive(ctx);
    }
}
