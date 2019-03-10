package com.mint.dao;

import com.mint.pojo.PostEntity;

public interface PostEntityMapper {
    int deleteByPrimaryKey(String tid);

    int insert(PostEntity record);

    int insertSelective(PostEntity record);

    PostEntity selectByPrimaryKey(String tid);

    int updateByPrimaryKeySelective(PostEntity record);

    int updateByPrimaryKeyWithBLOBs(PostEntity record);

    int updateByPrimaryKey(PostEntity record);
}