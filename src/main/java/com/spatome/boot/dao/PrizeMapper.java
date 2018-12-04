package com.spatome.boot.dao;

import com.spatome.boot.entity.Prize;

public interface PrizeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Prize record);

    int insertSelective(Prize record);

    Prize selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Prize record);

    int updateByPrimaryKey(Prize record);
}