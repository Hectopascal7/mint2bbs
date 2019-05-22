package com.mint.dao;

import com.mint.pojo.Good;
import com.mint.pojo.GoodWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodMapper {
    int deleteByPrimaryKey(String gid);

    int insert(GoodWithBLOBs record);

    int insertSelective(GoodWithBLOBs record);

    GoodWithBLOBs selectByPrimaryKey(String gid);

    int updateByPrimaryKeySelective(GoodWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GoodWithBLOBs record);

    int updateByPrimaryKey(Good record);

    List<GoodWithBLOBs> getGoodListWithPage(@Param("isused") Integer isused, @Param("order") String order, @Param("limit") int limit);
}