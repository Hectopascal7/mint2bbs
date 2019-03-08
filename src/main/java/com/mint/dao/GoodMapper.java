package com.mint.dao;

import com.mint.pojo.Good;
import com.mint.pojo.GoodWithBLOBs;

public interface GoodMapper {
    int deleteByPrimaryKey(String gid);

    int insert(GoodWithBLOBs record);

    int insertSelective(GoodWithBLOBs record);

    GoodWithBLOBs selectByPrimaryKey(String gid);

    int updateByPrimaryKeySelective(GoodWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GoodWithBLOBs record);

    int updateByPrimaryKey(Good record);
}