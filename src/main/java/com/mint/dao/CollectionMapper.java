package com.mint.dao;

import com.mint.pojo.Collection;

public interface CollectionMapper {
    int deleteByPrimaryKey(String cid);

    int insert(Collection record);

    int insertSelective(Collection record);

    Collection selectByPrimaryKey(String cid);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);
}