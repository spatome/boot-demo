package com.shby.message.service;

import com.shby.api.dto.msg.MsgPhoneMessageGroupDto;

/**
 * @author zhangwei
 * 
 * 短信服务
 * 群发
 *
 */
public interface MessagePhoneGroupService {

	public void execute(MsgPhoneMessageGroupDto dto);

	public boolean send(String phone, String content);
}
