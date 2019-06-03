package com.mint.dao;

import com.mint.pojo.Praise;
import org.apache.ibatis.annotations.Param;

public interface PraiseMapper {
    int deleteByPrimaryKey(String pid);

    int insert(Praise record);

    int insertSelective(Praise record);

    Praise selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(Praise record);

    int updateByPrimaryKey(Praise record);

    String checkPraise(@Param("iid") String iid, @Param("uid") String uid);
}