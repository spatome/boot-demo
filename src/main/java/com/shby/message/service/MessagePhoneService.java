package com.shby.message.service;

import com.shby.api.dto.msg.MsgPhoneMessageDto;

/**
 * @author zhangwei
 * 
 * 短信服务
 * 单发
 */
public interface MessagePhoneService {

	public void execute(MsgPhoneMessageDto dto);

	public boolean send(String phone, String content);
}
