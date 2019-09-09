package com.shby.message.dao;

import java.util.List;

import com.shby.message.entity.MsgTemplate;

public interface MsgTemplateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MsgTemplate record);

    int insertSelective(MsgTemplate record);

    MsgTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MsgTemplate record);

    int updateByPrimaryKey(MsgTemplate record);


    List<MsgTemplate> selectByBean(MsgTemplate msgTemplateQ);
}