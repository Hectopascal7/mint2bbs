package com.mint.dao;

import com.mint.pojo.Topic;

public interface TopicMapper {
    int deleteByPrimaryKey(Integer tid);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKey(Topic record);
}