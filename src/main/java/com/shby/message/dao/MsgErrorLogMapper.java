package com.shby.message.dao;

import java.util.List;

import com.shby.message.entity.MsgErrorLog;

public interface MsgErrorLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MsgErrorLog record);

    int insertSelective(MsgErrorLog record);

    MsgErrorLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MsgErrorLog record);

    int updateByPrimaryKey(MsgErrorLog record);


    List<MsgErrorLog> selectByBean(MsgErrorLog msgErrorLogQ);
}