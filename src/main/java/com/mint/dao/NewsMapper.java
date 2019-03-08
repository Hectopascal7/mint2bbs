package com.mint.dao;

import com.mint.pojo.News;

public interface NewsMapper {
    int deleteByPrimaryKey(String nid);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(String nid);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKeyWithBLOBs(News record);

    int updateByPrimaryKey(News record);
}