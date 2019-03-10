package com.mint.dao;

import com.mint.pojo.Ban;

public interface BanMapper {
    int deleteByPrimaryKey(String bid);

    int insert(Ban record);

    int insertSelective(Ban record);

    Ban selectByPrimaryKey(String bid);

    int updateByPrimaryKeySelective(Ban record);

    int updateByPrimaryKeyWithBLOBs(Ban record);

    int updateByPrimaryKey(Ban record);
}