package com.spatome.boot.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.spatome.boot.common.exception.SException;
import com.spatome.boot.factory.ServiceFactory;

public abstract class BaseService {

	@Autowired
	protected ServiceFactory serviceFactoryImpl;

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
