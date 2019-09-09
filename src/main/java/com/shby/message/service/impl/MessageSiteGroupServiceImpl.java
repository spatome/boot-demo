package com.shby.message.service.impl;

import org.springframework.stereotype.Service;

import com.shby.api.dto.msg.MsgAppMessageGroupDto;
import com.shby.message.service.BaseService;
import com.shby.message.service.MessageAppGroupService;

@Service
public class MessageSiteGroupServiceImpl extends BaseService implements MessageAppGroupService {

	@Override
	public void execute(MsgAppMessageGroupDto msgAppMessageGroupDto) {
		
	}

	@Override
	public boolean send(String phone, String content) {
		return false;
	}

}
