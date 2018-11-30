package com.spatome.boot.dao.basic;

import java.util.List;

import com.spatome.boot.entity.basic.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);


    List<SysUser> selectByBean(SysUser sysUserQ);
}