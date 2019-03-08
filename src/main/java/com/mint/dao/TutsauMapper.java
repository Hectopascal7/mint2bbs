package com.mint.dao;

import com.mint.pojo.Tutsau;

public interface TutsauMapper {
    int deleteByPrimaryKey(String tid);

    int insert(Tutsau record);

    int insertSelective(Tutsau record);

    Tutsau selectByPrimaryKey(String tid);

    int updateByPrimaryKeySelective(Tutsau record);

    int updateByPrimaryKeyWithBLOBs(Tutsau record);

    int updateByPrimaryKey(Tutsau record);
}