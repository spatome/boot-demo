package com.spatome.boot.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class NettyServerInitalizer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		//这里的1024,表示单条消息的最大长度,当达到长度后,还没有找到分隔符,则抛出TooLongFrameException
		pipeline.addLast(new DelimiterBasedFrameDecoder(1024, Delimiters.lineDelimiter()));
		//ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());	//自定义分隔符
		//pipeline.addLast(new DelimiterBasedFrameDecoder(8192, delimiter));
		pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
		pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
		pipeline.addLast(new NettyServerHandler());
	}

}
