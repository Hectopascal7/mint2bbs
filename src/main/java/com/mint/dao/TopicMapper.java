package com.mint.dao;

import com.mint.pojo.Topic;

public interface TopicMapper {
    int deleteByPrimaryKey(String tid);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(String tid);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKeyWithBLOBs(Topic record);

    int updateByPrimaryKey(Topic record);
}