package com.spatome.boot.dao;

import com.spatome.boot.entity.ActivityPrize;

public interface ActivityPrizeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ActivityPrize record);

    int insertSelective(ActivityPrize record);

    ActivityPrize selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityPrize record);

    int updateByPrimaryKey(ActivityPrize record);
}