package com.mint.dao;

import com.mint.pojo.Checkin;

public interface CheckinMapper {
    int deleteByPrimaryKey(String cid);

    int insert(Checkin record);

    int insertSelective(Checkin record);

    Checkin selectByPrimaryKey(String cid);

    int updateByPrimaryKeySelective(Checkin record);

    int updateByPrimaryKey(Checkin record);

    Checkin getUserLatestCheckinInfo(String uid);
}