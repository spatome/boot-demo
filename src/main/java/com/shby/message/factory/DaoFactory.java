package com.shby.message.factory;

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

public interface DaoFactory
{
	
	public SysConfigMapper getSysConfigMapper();
	public MsgErrorLogMapper getMsgErrorLogMapper();
	public MsgTemplateMapper getMsgTemplateMapper();

	public MsgPhoneChannelMapper getMsgPhoneChannelMapper();
	public MsgEmailChannelMapper getMsgEmailChannelMapper();

	public MsgPhoneMessageMapper getMsgPhoneMessageMapper();
	public MsgEmailMessageMapper getMsgEmailMessageMapper();
	public MsgSiteMessageMapper getMsgSiteMessageMapper();

	public MsgBatchPhoneMessageMapper getMsgBatchPhoneMessageMapper();
	public MsgBatchEmailMessageMapper getMsgBatchEmailMessageMapper();
	public MsgBatchSiteMessageMapper getMsgBatchSiteMessageMapper();
}
