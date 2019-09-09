package com.shby.message.service;

import com.shby.api.dto.msg.MsgMailMessageGroupDto;

/**
 * 邮箱服务
 * 群发
 *
 */
public interface MessageMailGroupService {

	public void execute(MsgMailMessageGroupDto dto);

	public boolean send(String[] toMails, String subject, String content, String contentType);
}
