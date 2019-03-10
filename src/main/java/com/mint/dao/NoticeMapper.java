package com.mint.dao;

import com.mint.pojo.Notice;
import com.mint.pojo.Post;

import java.util.List;

public interface NoticeMapper {
    int deleteByPrimaryKey(String tid);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(String tid);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKeyWithBLOBs(Notice record);

    int updateByPrimaryKey(Notice record);

    List<Notice> getNoticeBoard();
}