package com.spatome.boot.factory;

import com.spatome.boot.dao.AccountMapper;
import com.spatome.boot.dao.ActivityMapper;
import com.spatome.boot.dao.ActivityPrizeMapper;
import com.spatome.boot.dao.DrawLogMapper;
import com.spatome.boot.dao.EnterpriseMapper;
import com.spatome.boot.dao.PrizeMapper;
import com.spatome.boot.dao.SysPermissionMapper;
import com.spatome.boot.dao.SysRoleMapper;
import com.spatome.boot.dao.SysUserMapper;

public interface DaoFactory
{

	public SysPermissionMapper getSysPermissionMapper();
	public SysRoleMapper getSysRoleMapper();
	public SysUserMapper getSysUserMapper();

	public AccountMapper getAccountMapper();
	public EnterpriseMapper getEnterpriseMapper();

	public ActivityMapper getActivityMapper();
	public PrizeMapper getPrizeMapper();
	public ActivityPrizeMapper getActivityPrizeMapper();
	public DrawLogMapper getDrawLogMapper();

}
