package com.mint.dao;

import com.mint.pojo.Operation;

public interface OperationMapper {
    int deleteByPrimaryKey(String bid);

    int insert(Operation record);

    int insertSelective(Operation record);

    Operation selectByPrimaryKey(String bid);

    int updateByPrimaryKeySelective(Operation record);

    int updateByPrimaryKeyWithBLOBs(Operation record);

    int updateByPrimaryKey(Operation record);
}