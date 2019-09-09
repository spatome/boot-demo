package com.shby.message.dao;

import java.util.List;

import com.shby.message.entity.MsgBatchSiteMessage;

public interface MsgBatchSiteMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MsgBatchSiteMessage record);

    int insertSelective(MsgBatchSiteMessage record);

    MsgBatchSiteMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MsgBatchSiteMessage record);

    int updateByPrimaryKey(MsgBatchSiteMessage record);


    List<MsgBatchSiteMessage> selectByBean(MsgBatchSiteMessage msgBatchSiteMessageQ);
}