package com.spatome.boot.netty;

import com.spatome.boot.netty.bean.MyDecoder;
import com.spatome.boot.netty.bean.MyEncoder;
import com.spatome.boot.netty.proto.ClientMessage;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class NettyServerInitalizer extends ChannelInitializer<SocketChannel> {

	private final static int MAX_FRAME_LENGTH = 1024 * 1024;	//消息体的最大长度
	private final static int LENGTH_FIELD_OFFSET = 0;			//指的是长度域的偏移量，表示跳过指定长度个字节之后的才是长度域
	private final static int LENGTH_FIELD_LENGTH = 4;			//length字段的长度
	private final static int LENGTH_ADJUSTMENT = 0;
	private final static int INITIAL_BYTES_TO_STRIP = 4;		//丢弃4个字节

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new LengthFieldBasedFrameDecoder(
					MAX_FRAME_LENGTH,
					LENGTH_FIELD_OFFSET,
					LENGTH_FIELD_LENGTH,
					LENGTH_ADJUSTMENT,
					INITIAL_BYTES_TO_STRIP,
					true));								//接收方使用
		pipeline.addLast(new LengthFieldPrepender(4));	//发送方使用
		pipeline.addLast(new MyEncoder());
		pipeline.addLast(new MyDecoder(ClientMessage.class));
		pipeline.addLast(new NettyServerHandler());
	}

}
