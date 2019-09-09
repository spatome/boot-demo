package com.shby.message.dao;

import java.util.List;

import com.shby.message.entity.MsgBatchPhoneMessage;

public interface MsgBatchPhoneMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MsgBatchPhoneMessage record);

    int insertSelective(MsgBatchPhoneMessage record);

    MsgBatchPhoneMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MsgBatchPhoneMessage record);

    int updateByPrimaryKey(MsgBatchPhoneMessage record);


    List<MsgBatchPhoneMessage> selectByBean(MsgBatchPhoneMessage msgBatchPhoneMessageQ);
}