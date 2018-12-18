package com.spatome.boot.netty.bean;

import lombok.Data;

@Data
public class Message {

	//类型  系统编号 0xA 表示A系统，0xB 表示B系统
	private byte type;
	//信息标志  0xA 表示心跳包    0xC 表示超时包  0xC 业务信息包
	private byte flag;
	private int length;
	private String body;

	public Message(byte type, byte flag, int length, String body) {
		this.type = type;
		this.flag = flag;
		this.length = length;
		this.body = body;
	}
}
