package com.spatome.boot.netty;

import java.net.InetAddress;
import java.util.UUID;

import com.spatome.boot.netty.handler.IMessage;
import com.spatome.boot.netty.handler.ProFactory;
import com.spatome.boot.netty.proto.ClientMessage;
import com.spatome.boot.netty.proto.ServerMessage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler<ClientMessage> {

	private int count;

	@SuppressWarnings("unchecked")
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ClientMessage msg) throws Exception {
		try {
			//String clientId = ctx.channel().id().asLongText();
			log.info("==>服务端收到消息{}:{}", ++count, msg.toString());

			ServerMessage serverMessage = new ServerMessage(
					UUID.randomUUID().toString(),
					msg.getClientMessageId(),
					"OK",
					String.class
					);
			
			@SuppressWarnings("rawtypes")
			IMessage iMessage = ProFactory.getMessageObject(msg.getMessageObjClass());
			iMessage.messageHandler(msg.getMessageObj(), serverMessage);

			if(serverMessage != null){
				ctx.channel().writeAndFlush(serverMessage);	
			}
		}finally {
			//抛弃收到的数据
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("==>RamoteAddress:{} active!", ctx.channel().remoteAddress());

        String message = "Welcome to " + InetAddress.getLocalHost().getHostAddress();

        ServerMessage resp = new ServerMessage(
        		UUID.randomUUID().toString(),
        		null,
        		message,
        		String.class
        		);

        ctx.channel().writeAndFlush(resp);

        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	//当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("==>Channel is disconnected");
        super.channelInactive(ctx);
    }
}
