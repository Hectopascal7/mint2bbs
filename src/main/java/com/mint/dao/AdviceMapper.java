package com.mint.dao;

import com.mint.pojo.Advice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdviceMapper {
    int deleteByPrimaryKey(String tid);

    int insert(Advice record);

    int insertSelective(Advice record);

    Advice selectByPrimaryKey(String tid);

    int updateByPrimaryKeySelective(Advice record);

    int updateByPrimaryKeyWithBLOBs(Advice record);

    int updateByPrimaryKey(Advice record);

    List<Advice> getPostWithPage(@Param("skind") String skind, @Param("sorder") String sorder, @Param("start") int start, @Param("slimit") int slimit);
}