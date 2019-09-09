package com.shby.message.dao;

import java.util.List;

import com.shby.message.entity.MsgEmailChannel;

public interface MsgEmailChannelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MsgEmailChannel record);

    int insertSelective(MsgEmailChannel record);

    MsgEmailChannel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MsgEmailChannel record);

    int updateByPrimaryKey(MsgEmailChannel record);


    List<MsgEmailChannel> selectByBean(MsgEmailChannel msgEmailChannelQ);
}