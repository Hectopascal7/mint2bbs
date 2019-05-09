package com.mint.dao;

import com.mint.pojo.Post;
import org.apache.ibatis.annotations.Param;

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

//    List<PostEntity> getSectionPostWithPage1();
}