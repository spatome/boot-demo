package com.shby.message.service;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.shby.message.common.config.MyConfig;
import com.shby.message.common.exception.SException;
import com.shby.message.entity.MsgErrorLog;
import com.shby.message.factory.DaoFactory;
import com.shby.message.factory.ServiceFactory;

public abstract class BaseService {

	@Autowired
	protected MyConfig myConfig;
	@Autowired
	protected RabbitTemplate rabbitTemplate;
	@Autowired
	protected DaoFactory daoFactory;
	@Autowired
	protected ServiceFactory serviceFactory;

	/**
	 * @Description: 不空检查
	 */
	public void checkNotEmpty(Map<String, String> paramMap)
	{
		for (Map.Entry<String, String> entry : paramMap.entrySet())
		{
			if (entry.getValue()==null || entry.getValue().length()==0)
			{
				throw new SException("9999", entry.getKey() + "不能为空！");
			}
		}
	}

	public Long tranLong(String s){
		return StringUtils.isBlank(s) ? null : Long.valueOf(s);
	}
	
	/**
	 * 省事
	 */
	public void saveMsgErrorLog(Date date, String channelId, String channelType, String content, String descs){
		MsgErrorLog newMsgErrorLog = new MsgErrorLog();
		newMsgErrorLog.setCreateTime(date);
		newMsgErrorLog.setUpdateTime(date);
		newMsgErrorLog.setChannelId(channelId);
		newMsgErrorLog.setChannelType(channelType);
		newMsgErrorLog.setContent(content);
		newMsgErrorLog.setDescs(descs);
		daoFactory.getMsgErrorLogMapper().insertSelective(newMsgErrorLog);
	}

}
