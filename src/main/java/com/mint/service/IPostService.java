package com.mint.service;

import com.mint.common.ServerResponse;
import com.mint.pojo.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

public interface IPostService {

    /**
     * @Description 发帖
     * @Param loginid
     * @Param password
     * @Param session
     * @Return ServerResponse<User>
     */
    ServerResponse<String> post(String sid, String title, String content, HttpSession session);

    /**
     * @Description 获取社区公告板
     * @Return ServerResponse<List < Notice>>
     */
    ServerResponse<List<Notice>> getNoticeBoard();

    /**
     * @Description 获取置顶模块
     * @Return ServerResponse<List < Notice>>
     */
    ServerResponse<List<HashMap<String, String>>> getAllSticky();

    /**
     * @Description 获取热门帖子
     * @Return ServerResponse<List < Notice>>
     */
    ServerResponse<List<Post>> getHotPost();

    /**
     * @Description 获取帖子列表
     * @Return ServerResponse<List < Notice>>
     */
    ServerResponse<List<HashMap<String, String>>> getPostByPtime(int page, String kind, String order);

    /**
     * @Description 获取主页热门帖子
     * @Param sid
     * @Return ServerResponse<List < Notice>>
     */
    ServerResponse<List<Post>> getSectionHotPost(String sid);

    /**
     * @Description 获取板块内帖子列表
     * @Param section
     * @Param kind
     * @Param order
     * @Param page
     * @Param limit
     * @Return ServerResponse<List < Notice>>
     */
    ServerResponse<List<HashMap<String, String>>> getSectionPostWithPage(String section, String kind, String order, int page, int limit);

    /**
     * @Description 获取板块内帖子列表
     * @Param tid
     * @Param section
     * @Return ServerResponse<HashMap < String, Object>>
     */
    ServerResponse<HashMap<String, String>> getPostDetail(String tid, String section,HttpSession httpSession);

    /**
     * @Description 获取板块内帖子列表
     * @Param tid
     * @Param section
     * @Return ServerResponse<HashMap < String, Object>>
     */
    ServerResponse<List<Post>> getUserLatestTopic(String uid);

    ServerResponse<List<Post>> getHomeMoreTopic(String uid);

    ServerResponse<Integer> getSectionPostCount(String sid,String kind);

    ServerResponse<List<HashMap<String, String>>> getMyPostWithPage(Integer page, Integer limit, HttpSession httpSession);

    ServerResponse<Integer> getMyPostCount(HttpSession httpSession);
}
