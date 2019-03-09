package com.mint.dao;

import com.mint.pojo.Guide;

public interface GuideMapper {
    int deleteByPrimaryKey(String tid);

    int insert(Guide record);

    int insertSelective(Guide record);

    Guide selectByPrimaryKey(String tid);

    int updateByPrimaryKeySelective(Guide record);

    int updateByPrimaryKeyWithBLOBs(Guide record);

    int updateByPrimaryKey(Guide record);
}