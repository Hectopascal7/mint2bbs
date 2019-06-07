package com.mint.dao;

import com.mint.pojo.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyMapper {
    int deleteByPrimaryKey(String rid);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(String rid);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKeyWithBLOBs(Reply record);

    int updateByPrimaryKey(Reply record);

    List<Reply> getMainReplies(String tid);

    List<Reply> getUserLatestReply(String uid);

    List<Reply> getHomeMoreReply(String uid);

    Integer updateReplyPcount(@Param("rid") String rid, @Param("getCount") Integer getCount);

    Integer getReplyCount(@Param("tid") String tid);
}