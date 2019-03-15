package com.mint.dao;

import com.mint.pojo.Praise;

public interface PraiseMapper {
    int deleteByPrimaryKey(String pid);

    int insert(Praise record);

    int insertSelective(Praise record);

    Praise selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(Praise record);

    int updateByPrimaryKey(Praise record);
}