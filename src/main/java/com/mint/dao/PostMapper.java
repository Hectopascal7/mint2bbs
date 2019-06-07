package com.mint.dao;

import com.mint.pojo.Post;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PostMapper {
    int deleteByPrimaryKey(String tid);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(String tid);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKeyWithBLOBs(Post record);

    int updateByPrimaryKey(Post record);

    List<Post> getAllSticky();

    List<Post> getHotPost();

    List<Post> getPostByPtime(@Param("skind") String skind, @Param("sorder") String sorder, @Param("tcount") int tcount);

    List<Post> getSectionHotPost(@Param("tb_name") String tb_name);

    List<Post> getUserLatestTopic(String uid);

    List<Post> getHomeMoreTopic(String uid);

    Post getReplyPost(@Param("tb_name") String tb_name, @Param("tid") String tid);

    Integer getSectionPostCount(@Param("tb_name") String tb_name, @Param("kind") String kind);

    List<Post> getMyPostWithPage(@Param("start") Integer start, @Param("limit") Integer limit, @Param("uid") String uid);

    Integer getMyPostCount(String uid);

    Post getPostByTid(@Param("tid") String tid, @Param("tb_name") String tb_name);

    Post getReceiveUidByTid(@Param("tid") String tid);

    List<Post> getSectionPostWithPage(@Param("tb_name") String tb_name, @Param("kind") String kind, @Param("order") String order, @Param("start") int start, @Param("limit") int limit);

    Post getSectionPostDetail(@Param("tb_name") String tb_name, @Param("tid") String tid);

    Integer post(@Param("tb_name") String tb_name, @Param("tid") String tid, @Param("uid") String uid, @Param("sid") String sid, @Param("title") String title, @Param("ptime") Date ptime, @Param("content") String content);

    Integer updatePostCount(@Param("tb_name") String tb_name, @Param("tid") String tid, @Param("countName") String countName, @Param("getCount") Integer getCount);
}