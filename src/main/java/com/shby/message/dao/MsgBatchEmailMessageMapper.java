package com.shby.message.dao;

import java.util.List;

import com.shby.message.entity.MsgBatchEmailMessage;

public interface MsgBatchEmailMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MsgBatchEmailMessage record);

    int insertSelective(MsgBatchEmailMessage record);

    MsgBatchEmailMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MsgBatchEmailMessage record);

    int updateByPrimaryKey(MsgBatchEmailMessage record);

    List<MsgBatchEmailMessage> selectByBean(MsgBatchEmailMessage msgBatchEmailMessageQ);
}