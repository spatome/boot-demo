package com.spatome.boot.netty.handler;

public interface IMessage<T> {

	public void messageHandler(String serverMessageId, String clientMessageId, T message);
}
