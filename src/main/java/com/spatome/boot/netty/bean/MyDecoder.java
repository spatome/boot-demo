package com.spatome.boot.netty.bean;

import java.util.List;

import com.spatome.boot.netty.util.ProtostuffUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 继承MessageToMessageDecoder
 */
public class MyDecoder extends ByteToMessageDecoder {

    // 需要反序列对象所属的类型
    private Class<?> clazz;

    // 构造方法，传入需要反序列化对象的类型
    public MyDecoder(Class<?> clazz) {
        this.clazz = clazz;
    }
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        // ByteBuf的长度
        int size = msg.readableBytes();
        // 构建length长度的字节数组
        byte[] data = new byte[size];
        // 将ByteBuf数据复制到字节数组中
        msg.readBytes(data);
        // 反序列化对象
        Object obj = ProtostuffUtil.deserialize(data, this.clazz);
        // 添加到反序列化对象结果列表
        out.add(obj);
	}

}
