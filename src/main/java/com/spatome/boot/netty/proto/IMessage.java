package com.spatome.boot.netty.proto;

public interface IMessage<T> {

	public void messageHandler(String serverMessageId, String clientMessageId, T message);
}
