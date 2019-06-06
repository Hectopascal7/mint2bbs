package com.mint.controller.portal;

import com.alibaba.fastjson.JSON;
import com.mint.common.ServerResponse;
import com.mint.pojo.*;
import com.mint.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * @Program: mint2bbs
 * @Description: 帖子Action
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-07 20:55:31
 **/
@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private IPostService iPostService;

    /**
     * @Description 发帖
     * @Param loginid
     * @Param password
     * @Param session
     * @Return ServerResponse<User>
     */
    @RequestMapping(value = "post.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> post(String sid, String title, String content, HttpSession session) {
        ServerResponse<String> response = iPostService.post(sid, title, content, session);
        return response;
    }

    /**
     * @Description 获取社区公告板
     * @Return ServerResponse<List < Notice>>
     */
    @RequestMapping(value = "getNoticeBoard.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<Notice>> getNoticeBoard() {
        ServerResponse<List<Notice>> response = iPostService.getNoticeBoard();
        return response;
    }

    /**
     * @Description 获取置顶模块
     * @Return ServerResponse<List < Notice>>
     */
    @RequestMapping(value = "getAllSticky.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<HashMap<String, String>>> getAllSticky() {
        ServerResponse<List<HashMap<String, String>>> response = iPostService.getAllSticky();
        return response;
    }

    /**
     * @Description 获取主页热门帖子
     * @Return ServerResponse<List < Notice>>
     */
    @RequestMapping(value = "getHotPost.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<Post>> getHotPost() {
        ServerResponse<List<Post>> response = iPostService.getHotPost();
        return response;
    }

    /**
     * @Description 获取板块热门帖子
     * @Param sid
     * @Return ServerResponse<List < Notice>>
     */
    @RequestMapping(value = "getSectionHotPost.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<Post>> getSectionHotPost(String section) {
        ServerResponse<List<Post>> response = iPostService.getSectionHotPost(section);
        return response;
    }

    /**
     * @Description 帖子模块
     * @Return ServerResponse<List < Notice>>
     */
    @RequestMapping(value = "getPostByPtime.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<HashMap<String, String>>> getPostByPtime(@RequestParam("page") int page, @RequestParam("kind") String kind, @RequestParam("order") String order) {
        ServerResponse<List<HashMap<String, String>>> response = iPostService.getPostByPtime(page, kind, order);
        return response;
    }

    /**
     * @Description 获取板块内帖子列表
     * @Return ServerResponse<List < Notice>>
     */
    @RequestMapping(value = "getSectionPostWithPage.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<HashMap<String, String>>> getSectionPostWithPage
    (@RequestParam("section") String section, @RequestParam("kind")
            String kind, @RequestParam("order") String order,
     @RequestParam("page") int page, @RequestParam("limit") int limit) {
        ServerResponse<List<HashMap<String, String>>> response = iPostService.getSectionPostWithPage(section, kind, order, page, limit);
        System.out.println(JSON.toJSONString(response));
        return response;
    }

    /**
     * @Description 获取板块内帖子数量
     * @Return ServerResponse<List < Notice>>
     */
    @RequestMapping(value = "getSectionPostCount.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Integer> getSectionPostCount(@RequestParam("sid") String sid, @RequestParam("kind") String kind) {
        ServerResponse<Integer> response = iPostService.getSectionPostCount(sid, kind);
        return response;
    }

    /**
     * @Description 获取板块内帖子列表
     * @Param tid
     * @Param section
     * @Return ServerResponse<HashMap < String, Object>>
     */
    @RequestMapping(value = "getPostDetail.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<HashMap<String, String>> getPostDetail(String tid, String section, HttpSession httpSession) {
        return iPostService.getPostDetail(tid, section, httpSession);
    }

    /**
     * @Description 获取帖子回复
     * @Param tid
     * @Param section
     * @Return ServerResponse<HashMap < String, Object>>
     */
    @RequestMapping(value = "getUserLatestTopic.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<Post>> getUserLatestTopic(String uid) {
        ServerResponse<List<Post>> response = iPostService.getUserLatestTopic(uid);
        return response;
    }


    /**
     * @Description 获取帖子回复
     * @Param tid
     * @Param section
     * @Return ServerResponse<HashMap < String, Object>>
     */
    @RequestMapping(value = "getHomeMoreTopic.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<Post>> getHomeMoreTopic(String uid) {
        ServerResponse<List<Post>> response = iPostService.getHomeMoreTopic(uid);
        return response;
    }

    /**
     * @Description 获取帖子回复
     * @Param tid
     * @Param section
     * @Return ServerResponse<HashMap < String, Object>>
     */
    @RequestMapping(value = "getMyPostCount.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Integer> getMyPostCount(HttpSession httpSession) {
        return iPostService.getMyPostCount(httpSession);
    }

    /**
     * @Description 获取帖子回复
     * @Param tid
     * @Param section
     * @Return ServerResponse<HashMap < String, Object>>
     */
    @RequestMapping(value = "getMyPostWithPage.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<HashMap<String, String>>> getMyPostWithPage(Integer page, Integer limit, HttpSession httpSession) {
        return iPostService.getMyPostWithPage(page, limit, httpSession);
    }

}
