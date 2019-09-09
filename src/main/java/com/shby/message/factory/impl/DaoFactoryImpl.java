package com.shby.message.factory.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.shby.message.dao.MsgBatchEmailMessageMapper;
import com.shby.message.dao.MsgBatchPhoneMessageMapper;
import com.shby.message.dao.MsgBatchSiteMessageMapper;
import com.shby.message.dao.MsgEmailChannelMapper;
import com.shby.message.dao.MsgEmailMessageMapper;
import com.shby.message.dao.MsgErrorLogMapper;
import com.shby.message.dao.MsgPhoneChannelMapper;
import com.shby.message.dao.MsgPhoneMessageMapper;
import com.shby.message.dao.MsgSiteMessageMapper;
import com.shby.message.dao.MsgTemplateMapper;
import com.shby.message.dao.SysConfigMapper;
import com.shby.message.factory.DaoFactory;

@Lazy
@Service
public class DaoFactoryImpl implements DaoFactory {

	@Autowired
	private SysConfigMapper sysConfigMapper;
	@Autowired
	private MsgErrorLogMapper msgErrorLogMapper;
	@Autowired
	private MsgTemplateMapper msgTemplateMapper;

	@Autowired
	private MsgPhoneChannelMapper msgPhoneChannelMapper;
	@Autowired
	private MsgEmailChannelMapper msgEmailChannelMapper;

	@Autowired
	private MsgPhoneMessageMapper msgPhoneMessageMapper;
	@Autowired
	private MsgEmailMessageMapper msgEmailMessageMapper;
	@Autowired
	private MsgSiteMessageMapper msgSiteMessageMapper;

	@Autowired
	private MsgBatchPhoneMessageMapper msgBatchPhoneMessageMapper;
	@Autowired
	private MsgBatchEmailMessageMapper msgBatchEmailMessageMapper;
	@Autowired
	private MsgBatchSiteMessageMapper msgBatchSiteMessageMapper;	
	
	@Override
	public SysConfigMapper getSysConfigMapper() {
		return sysConfigMapper;
	}

	@Override
	public MsgErrorLogMapper getMsgErrorLogMapper() {
		return msgErrorLogMapper;
	}

	@Override
	public MsgTemplateMapper getMsgTemplateMapper() {
		return msgTemplateMapper;
	}

	@Override
	public MsgPhoneChannelMapper getMsgPhoneChannelMapper() {
		return msgPhoneChannelMapper;
	}

	@Override
	public MsgEmailChannelMapper getMsgEmailChannelMapper() {
		return msgEmailChannelMapper;
	}

	@Override
	public MsgPhoneMessageMapper getMsgPhoneMessageMapper() {
		return msgPhoneMessageMapper;
	}

	@Override
	public MsgEmailMessageMapper getMsgEmailMessageMapper() {
		return msgEmailMessageMapper;
	}

	@Override
	public MsgSiteMessageMapper getMsgSiteMessageMapper() {
		return msgSiteMessageMapper;
	}

	@Override
	public MsgBatchPhoneMessageMapper getMsgBatchPhoneMessageMapper() {
		return msgBatchPhoneMessageMapper;
	}

	@Override
	public MsgBatchEmailMessageMapper getMsgBatchEmailMessageMapper() {
		return msgBatchEmailMessageMapper;
	}

	@Override
	public MsgBatchSiteMessageMapper getMsgBatchSiteMessageMapper() {
		return msgBatchSiteMessageMapper;
	}

}