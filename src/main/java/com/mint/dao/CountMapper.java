package com.mint.dao;

import com.mint.pojo.Count;

import java.util.List;

public interface CountMapper {
    int deleteByPrimaryKey(String uid);

    int insert(Count record);

    int insertSelective(Count record);

    Count selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(Count record);

    int updateByPrimaryKey(Count record);

    List<Count> getActiveUser();
}