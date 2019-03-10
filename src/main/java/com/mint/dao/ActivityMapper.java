package com.mint.dao;

import com.mint.pojo.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityMapper {
    int deleteByPrimaryKey(String tid);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(String tid);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKeyWithBLOBs(Activity record);

    int updateByPrimaryKey(Activity record);

    List<Activity> getPostWithPage(@Param("skind") String skind, @Param("sorder") String sorder, @Param("start") int start, @Param("slimit") int slimit);
}