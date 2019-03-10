package com.mint.dao;

import com.mint.pojo.Guide;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuideMapper {
    int deleteByPrimaryKey(String tid);

    int insert(Guide record);

    int insertSelective(Guide record);

    Guide selectByPrimaryKey(String tid);

    int updateByPrimaryKeySelective(Guide record);

    int updateByPrimaryKeyWithBLOBs(Guide record);

    int updateByPrimaryKey(Guide record);

    List<Guide> getPostWithPage(@Param("skind") String skind, @Param("sorder") String sorder, @Param("start") int start, @Param("slimit") int slimit);
}