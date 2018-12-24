package com.spatome.boot.netty;

import com.spatome.boot.netty.handler.IMessage;
import com.spatome.boot.netty.handler.ProFactory;
import com.spatome.boot.netty.proto.ClientMessage;
import com.spatome.boot.netty.proto.ServerMessage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyClientHandler extends SimpleChannelInboundHandler<ServerMessage> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ServerMessage msg) throws Exception {
		try {
			log.info("==>收到服务端消息:{}", msg.toString());

			IMessage iMessage = ProFactory.getMessageObject(msg.getMessageObjClass());

			ClientMessage clientMessage = new ClientMessage(msg.getServerMessageId(), msg.getClientMessageId(), "", String.class);
			iMessage.messageHandler(msg.getMessageObj(), clientMessage);
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
