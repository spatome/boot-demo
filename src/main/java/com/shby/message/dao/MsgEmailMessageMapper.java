package com.shby.message.dao;

import java.util.List;

import com.shby.message.entity.MsgEmailMessage;

public interface MsgEmailMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MsgEmailMessage record);

    int insertSelective(MsgEmailMessage record);

    MsgEmailMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MsgEmailMessage record);

    int updateByPrimaryKey(MsgEmailMessage record);


    List<MsgEmailMessage> selectByBean(MsgEmailMessage msgEmailMessageQ);
    void batchInsert(List<MsgEmailMessage> recordList);
}