package com.spatome.boot.netty;

import java.net.InetAddress;
import java.util.UUID;

import com.spatome.boot.netty.proto.ClientMessage;
import com.spatome.boot.netty.proto.ServerMessage;
import com.spatome.boot.netty.proto.ActivePro;
import com.spatome.boot.netty.proto.UserPro;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler<ClientMessage> {

	private int count;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ClientMessage msg) throws Exception {
		try {
			//String clientId = ctx.channel().id().asLongText();
			log.info("==>服务端收到消息{}:{}", ++count, msg.toString());

			//返回
			UserPro userPro = new UserPro();
			userPro.setUserName("zw"+count);
	        ServerMessage resp = new ServerMessage(
	        		UUID.randomUUID().toString(),
	        		msg.getClientMessageId(),
	        		userPro,
	        		UserPro.class
	        		);

			ctx.channel().writeAndFlush(resp);
		}finally {
			//抛弃收到的数据
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("==>RamoteAddress:{} active!", ctx.channel().remoteAddress());

        String message = "Welcome to " + InetAddress.getLocalHost().getHostAddress();
        ActivePro activePro = new ActivePro();
        activePro.setValue(message);

        ServerMessage resp = new ServerMessage(
        		UUID.randomUUID().toString(),
        		null,
        		activePro,
        		ActivePro.class
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
