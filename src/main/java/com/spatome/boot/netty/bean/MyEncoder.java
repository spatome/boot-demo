package com.spatome.boot.netty.bean;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码
 */
public class MyEncoder extends MessageToByteEncoder<Message> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Message message, ByteBuf out) throws Exception {
		if(message==null){
			throw new RuntimeException("message is null");
		}

		out.writeByte(message.getType());
		out.writeByte(message.getFlag());
		byte[] data = message.getBody().getBytes("UTF-8");
		out.writeInt(data.length);
		out.writeBytes(data);
	}

}
