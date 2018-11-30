package com.spatome.boot.factory.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.spatome.boot.dao.basic.SysUserMapper;
import com.spatome.boot.dao.zjd.EnterpriseMapper;
import com.spatome.boot.factory.DaoFactory;

@Lazy
@Service
public class DaoFactoryImpl implements DaoFactory
{
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private EnterpriseMapper enterpriseMapper;

	@Override
	public EnterpriseMapper getEnterpriseMapper() {
		return enterpriseMapper;
	}

	@Override
	public SysUserMapper getSysUserMapper() {
		return sysUserMapper;
	}

}