package com.spatome.boot.factory;

import com.spatome.boot.dao.basic.SysUserMapper;
import com.spatome.boot.dao.zjd.EnterpriseMapper;

public interface DaoFactory
{

	public SysUserMapper getSysUserMapper();
	public EnterpriseMapper getEnterpriseMapper();

}
