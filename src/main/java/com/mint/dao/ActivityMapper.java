package com.mint.dao;

import com.mint.pojo.Activity;

public interface ActivityMapper {
    int deleteByPrimaryKey(String tid);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(String tid);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKeyWithBLOBs(Activity record);

    int updateByPrimaryKey(Activity record);
}