package com.mint.dao;

import com.mint.pojo.Advice;

public interface AdviceMapper {
    int deleteByPrimaryKey(String tid);

    int insert(Advice record);

    int insertSelective(Advice record);

    Advice selectByPrimaryKey(String tid);

    int updateByPrimaryKeySelective(Advice record);

    int updateByPrimaryKeyWithBLOBs(Advice record);

    int updateByPrimaryKey(Advice record);
}