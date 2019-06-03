package com.mint.dao;

import com.mint.pojo.Good;
import com.mint.pojo.GoodWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
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

    List<Good> getGoodListByUid(String uid);

    Integer updateGoodPrice(@Param("gid") String gid,@Param("price") BigDecimal newPrice);

    Integer setGoodIsSaled(String gid);

    List<Good> getHotGood();
}