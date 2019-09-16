package com.shby.message.service;

import com.shby.api.dto.msg.MsgPhoneMessageDto;
import com.shby.message.oapi.ResultBO;

/**
 * @author zhangwei
 * 
 * 短信服务
 * 单发
 */
public interface MessagePhoneService {

	public void execute(MsgPhoneMessageDto dto);

	public ResultBO send(String phone, String content);
}
