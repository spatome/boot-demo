package com.shby.message.service;

import com.shby.api.dto.msg.MsgSiteMessageDto;

/**
 * 站内消息
 * 单发
 */
public interface MessageSiteService {

	public void execute(MsgSiteMessageDto dto);

	public boolean send(String to, String title, String content);
}
