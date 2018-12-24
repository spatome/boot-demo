package com.spatome.boot.netty.handler;

import com.spatome.boot.netty.proto.ServerMessage;

public interface IMessage<T> {

	/**
	 * @param message	收到的消息
	 * @param serverMessage 返回的消息
	 * @return
	 */
	public void messageHandler(T message, ServerMessage serverMessage);
}
