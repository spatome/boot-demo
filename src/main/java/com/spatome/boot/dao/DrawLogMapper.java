package com.spatome.boot.dao;

import com.spatome.boot.entity.DrawLog;

public interface DrawLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DrawLog record);

    int insertSelective(DrawLog record);

    DrawLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DrawLog record);

    int updateByPrimaryKey(DrawLog record);
}