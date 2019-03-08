package com.mint.dao;

import com.mint.pojo.Activity;

public interface ActivityMapper {
    int deleteByPrimaryKey(String title);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(String title);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKeyWithBLOBs(Activity record);

    int updateByPrimaryKey(Activity record);
}