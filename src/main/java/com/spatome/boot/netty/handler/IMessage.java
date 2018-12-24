package com.spatome.boot.netty.handler;

import com.spatome.boot.netty.proto.ClientMessage;

public interface IMessage<T> {

	public void messageHandler(T message, ClientMessage clientMessage);
}
