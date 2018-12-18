package com.spatome.boot.netty.bean;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 解码
 */
public class MyDecoder extends LengthFieldBasedFrameDecoder {

	private int headSize;

	/**
	 * @param maxFrameLength	帧的最大长度
	 * @param lengthFieldOffset	length字段偏移的地址
	 * @param lengthFieldLength	length字段所占的字节长
	 * @param lengthAdjustment	修改帧数据长度字段中定义的值，可以为负数 因为有时候我们习惯把头部记入长度,若为负数,则说明要推后多少个字段
	 * @param initialBytesToStrip	解析时候跳过多少个长度
	 * @param failFast	为true，当frame长度超过maxFrameLength时立即报TooLongFrameException异常，为false，读取完整个帧再报异
	 */
	public MyDecoder(
			final int headSize,
			int maxFrameLength,
			int lengthFieldOffset,
			int lengthFieldLength,
			int lengthAdjustment,
			int initialBytesToStrip,
			boolean failFast) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip, failFast);
		this.headSize = headSize;
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		if(in==null){
			return null;
		}

		if(in.readableBytes() < headSize){
            throw new Exception("可读信息小于头部");
		}

		byte type = in.readByte();
		byte flag = in.readByte();
		int length = in.readInt();

		if(in.readableBytes() < length){
            throw new Exception("可读信息体小于length:"+length);
		}

        byte[] data = new byte[length];
        in.readBytes(data);

        return new Message(type, flag, length, new String(data, "UTF-8"));
	}

}
