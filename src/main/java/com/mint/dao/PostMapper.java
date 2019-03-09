package com.mint.dao;

import com.mint.pojo.Post;

public interface PostMapper {
    int deleteByPrimaryKey(String tid);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(String tid);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKeyWithBLOBs(Post record);

    int updateByPrimaryKey(Post record);
}