package com.mint.dao;

import com.mint.pojo.Notice;
import org.apache.ibatis.annotations.Param;

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

    List<Notice> getPostWithPage(@Param("skind") String skind, @Param("sorder") String sorder, @Param("start") int start, @Param("slimit") int slimit);


}