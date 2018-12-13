package com.spatome.boot.netty;

import java.net.InetAddress;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		try {
			//String clientId = ctx.channel().id().asLongText();
			log.info("==>收到消息:{}", msg);

			//测试原值返回
			ctx.channel().writeAndFlush(msg);
		}finally {
			//抛弃收到的数据
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("==>RamoteAddress:{} active!", ctx.channel().remoteAddress());
        ctx.channel().writeAndFlush( "Welcome to " + InetAddress.getLocalHost().getHostAddress() + " service!\n");

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
