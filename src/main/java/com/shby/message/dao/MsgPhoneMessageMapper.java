package com.shby.message.dao;

import java.util.List;

import com.shby.message.entity.MsgPhoneMessage;

public interface MsgPhoneMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MsgPhoneMessage record);

    int insertSelective(MsgPhoneMessage record);

    MsgPhoneMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MsgPhoneMessage record);

    int updateByPrimaryKey(MsgPhoneMessage record);


    List<MsgPhoneMessage> selectByBean(MsgPhoneMessage msgPhoneMessageQ);
    void batchInsert(List<MsgPhoneMessage> recordList);
}