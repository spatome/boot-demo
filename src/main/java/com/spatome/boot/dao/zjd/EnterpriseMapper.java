package com.spatome.boot.dao.zjd;

import java.util.List;

import com.spatome.boot.entity.zjd.Enterprise;

public interface EnterpriseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Enterprise record);

    int insertSelective(Enterprise record);

    Enterprise selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Enterprise record);

    int updateByPrimaryKey(Enterprise record);


    List<Enterprise> selectByBean(Enterprise enterpriseQ);
}