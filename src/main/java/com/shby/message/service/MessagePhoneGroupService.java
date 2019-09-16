package com.shby.message.service;

import com.shby.api.dto.msg.MsgPhoneMessageGroupDto;
import com.shby.message.oapi.ResultBO;

/**
 * @author zhangwei
 * 
 * 短信服务
 * 群发
 *
 */
public interface MessagePhoneGroupService {

	public void execute(MsgPhoneMessageGroupDto dto);

	public ResultBO send(String phone, String content);
}
