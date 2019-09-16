package com.shby.message.service;

import com.shby.api.dto.msg.MsgSiteMessageGroupDto;

/**
 * 站内消息
 * 群发
 *
 */
public interface MessageSiteGroupService {

	public void execute(MsgSiteMessageGroupDto dto);

	public boolean send(String to, String title, String content);
}
