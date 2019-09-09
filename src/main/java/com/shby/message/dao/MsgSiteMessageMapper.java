package com.shby.message.dao;

import java.util.List;

import com.shby.message.entity.MsgSiteMessage;

public interface MsgSiteMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MsgSiteMessage record);

    int insertSelective(MsgSiteMessage record);

    MsgSiteMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MsgSiteMessage record);

    int updateByPrimaryKey(MsgSiteMessage record);


    List<MsgSiteMessage> selectByBean(MsgSiteMessage msgSiteMessageQ);
    void batchInsert(List<MsgSiteMessage> recordList);
}