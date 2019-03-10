package com.mint.dao;

import com.mint.pojo.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsMapper {
    int deleteByPrimaryKey(String tid);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(String tid);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKeyWithBLOBs(News record);

    int updateByPrimaryKey(News record);

    List<News> getPostWithPage(@Param("skind") String skind, @Param("sorder") String sorder, @Param("start") int start, @Param("slimit") int slimit);
}