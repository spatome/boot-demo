package com.spatome.boot.netty;

import com.spatome.boot.netty.bean.MyDecoder;
import com.spatome.boot.netty.bean.MyEncoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class NettyServerInitalizer extends ChannelInitializer<SocketChannel> {

	private static final int MAX_FRAME_LENGTH = 1024 * 1024;	// 最大长度,超出包的最大长度netty将会做一些特殊处理
	private static final int LENGTH_FIELD_LENGTH = 4;			// 长度字段所占的字节数
	private static final int LENGTH_FIELD_OFFSET = 2;			// 长度偏移
	private static final int LENGTH_ADJUSTMENT = 0;				// 该字段加长度字段等于数据帧的长度，包体长度调整的大小，长度域的数值表示的长度加上这个修正值表示的就是带header的包
	private static final int INITIAL_BYTES_TO_STRIP = 0;		// 从数据帧中跳过的字节数，表示获取完一个完整的数据包之后，忽略前面的指定的位数个字节，应用解码器拿到的就是不带长度域的数据包

	 /**
     * type占1个字节,flag占一个字节,length占4个字节所以头部总长度是6个字节
     */
	private static final int HEADER_SIZE = 6;
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("decoder", new MyDecoder(HEADER_SIZE, MAX_FRAME_LENGTH, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH, LENGTH_ADJUSTMENT, INITIAL_BYTES_TO_STRIP, true));
		pipeline.addLast("encoder", new MyEncoder());
		pipeline.addLast(new NettyServerHandler());
	}

}
