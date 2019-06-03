package com.mint.dao;

import com.mint.pojo.Collection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectionMapper {
    int deleteByPrimaryKey(String cid);

    int insert(Collection record);

    int insertSelective(Collection record);

    Collection selectByPrimaryKey(String cid);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);

    String checkCollection(@Param("iid") String iid, @Param("uid") String uid);

    Integer getMyCollectionCount(String uid);

    List<Collection> getMyCollectionWithPage(@Param("start") Integer start,@Param("limit") Integer limit,@Param("uid") String uid);
}