package com.mint.dao;

import com.mint.pojo.Notice;

public interface NoticeMapper {
    int deleteByPrimaryKey(String nid);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(String nid);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKeyWithBLOBs(Notice record);

    int updateByPrimaryKey(Notice record);
}