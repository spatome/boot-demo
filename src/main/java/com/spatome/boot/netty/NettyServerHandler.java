package com.spatome.boot.netty;

import java.net.InetAddress;

import com.spatome.boot.netty.bean.Message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

	private int count;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		try {
			//String clientId = ctx.channel().id().asLongText();
			log.info("==>服务端收到消息{}:{}", ++count, msg);

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
        Message message = new Message((byte)0xA, (byte)0xC, 0, "Welcome to " + InetAddress.getLocalHost().getHostAddress());
        ctx.channel().writeAndFlush(message);

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
