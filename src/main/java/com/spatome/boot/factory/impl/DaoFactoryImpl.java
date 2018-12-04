package com.spatome.boot.factory.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.spatome.boot.dao.AccountMapper;
import com.spatome.boot.dao.ActivityMapper;
import com.spatome.boot.dao.ActivityPrizeMapper;
import com.spatome.boot.dao.DrawLogMapper;
import com.spatome.boot.dao.EnterpriseMapper;
import com.spatome.boot.dao.PrizeMapper;
import com.spatome.boot.dao.SysPermissionMapper;
import com.spatome.boot.dao.SysRoleMapper;
import com.spatome.boot.dao.SysUserMapper;
import com.spatome.boot.factory.DaoFactory;

@Lazy
@Service
public class DaoFactoryImpl implements DaoFactory
{
	
	@Autowired
	public SysPermissionMapper sysPermissionMapper;
	@Autowired
	public SysRoleMapper sysRoleMapper;
	@Autowired
	public SysUserMapper sysUserMapper;

	@Autowired
	public AccountMapper accountMapper;
	@Autowired
	public EnterpriseMapper enterpriseMapper;

	@Autowired
	public ActivityMapper activityMapper;
	@Autowired
	public PrizeMapper prizeMapper;
	@Autowired
	public ActivityPrizeMapper activityPrizeMapper;
	@Autowired
	public DrawLogMapper drawLogMapper;

	@Override
	public SysPermissionMapper getSysPermissionMapper() {
		return sysPermissionMapper;
	}

	@Override
	public SysRoleMapper getSysRoleMapper() {
		return sysRoleMapper;
	}

	@Override
	public SysUserMapper getSysUserMapper() {
		return sysUserMapper;
	}

	@Override
	public AccountMapper getAccountMapper() {
		return accountMapper;
	}

	@Override
	public EnterpriseMapper getEnterpriseMapper() {
		return enterpriseMapper;
	}

	@Override
	public ActivityMapper getActivityMapper() {
		return activityMapper;
	}

	@Override
	public PrizeMapper getPrizeMapper() {
		return prizeMapper;
	}

	@Override
	public ActivityPrizeMapper getActivityPrizeMapper() {
		return activityPrizeMapper;
	}

	@Override
	public DrawLogMapper getDrawLogMapper() {
		return drawLogMapper;
	}

}