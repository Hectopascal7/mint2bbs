package com.mint.dao;

import com.mint.pojo.Resident;

public interface ResidentMapper {
    int deleteByPrimaryKey(String uid);

    int insert(Resident record);

    int insertSelective(Resident record);

    Resident selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(Resident record);

    int updateByPrimaryKey(Resident record);

    Resident getUserByLicense(String license);
}