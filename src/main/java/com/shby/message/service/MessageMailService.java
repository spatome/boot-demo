package com.shby.message.service;

import com.shby.api.dto.msg.MsgMailMessageDto;

/**
 * 邮箱服务
 * 单发
 * (切换channel功能需优化)
 */
public interface MessageMailService {

	public void execute(MsgMailMessageDto dto);

	/**
	 * @param toMail 接收的邮箱
	 * @param subJect 主题
	 * @param content 内容
	 * @param contentType 内容类型：T-text H-html
	 * @return
	 */
	public boolean send(String toMail, String subject, String content, String contentType);
}
