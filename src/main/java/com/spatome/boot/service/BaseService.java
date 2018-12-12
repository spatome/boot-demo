package com.spatome.boot.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.spatome.boot.common.exception.SException;
import com.spatome.boot.factory.ServiceFactory;
import com.spatome.boot.netty.config.NettyConfig;

public abstract class BaseService {
	@Autowired
	protected ServiceFactory serviceFactory;

	@Autowired
	protected NettyConfig nettyConfig;
	
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
}
