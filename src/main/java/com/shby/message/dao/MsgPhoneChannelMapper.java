package com.shby.message.dao;

import java.util.List;

import com.shby.message.entity.MsgPhoneChannel;

public interface MsgPhoneChannelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MsgPhoneChannel record);

    int insertSelective(MsgPhoneChannel record);

    MsgPhoneChannel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MsgPhoneChannel record);

    int updateByPrimaryKey(MsgPhoneChannel record);


    List<MsgPhoneChannel> selectByBean(MsgPhoneChannel msgPhoneChannelQ);
}