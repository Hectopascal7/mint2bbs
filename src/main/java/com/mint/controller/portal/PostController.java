package com.mint.controller.portal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mint.common.ServerResponse;
import com.mint.pojo.Notice;
import com.mint.pojo.Post;
import com.mint.pojo.Topic;
import com.mint.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.ObjectName;
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
    public ServerResponse<String> post(Topic topic, String partid, HttpSession session) {
        ServerResponse<String> response = iPostService.post(topic, partid, session);
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
     * @Description 置顶模块
     * @Return ServerResponse<List < Notice>>
     */
    @RequestMapping(value = "getAllSticky.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<HashMap<String, String>>> getAllSticky() {
        ServerResponse<List<HashMap<String, String>>> response = iPostService.getAllSticky();
        System.out.println(JSON.toJSONString(response));
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
     * @Description 获取主页热门帖子
     * @Param sid
     * @Return ServerResponse<List < Notice>>
     */
    @RequestMapping(value = "getSectionHotPost.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<Post>> getSectionHotPost(String section) {
        System.out.println("通了通了");
        ServerResponse<List<Post>> response = iPostService.getSectionHotPost(section);
        return response;
    }

    /**
     * @Description 帖子模块
     * @Return ServerResponse<List < Notice>>
     */
    @RequestMapping(value = "getPostByPtime.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<HashMap<String, Object>>> getPostByPtime(@RequestParam("page") int page) {
        ServerResponse<List<HashMap<String, Object>>> response = iPostService.getPostByPtime(page);
        System.out.println("123456" + JSON.toJSONString(response));
        return response;
    }

    /**
     * @Description 获取板块内帖子列表
     * @Return ServerResponse<List < Notice>>
     */
    @RequestMapping(value = "getSectionPostWithPage.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<HashMap<String, Object>>> getSectionPostWithPage
    (@RequestParam("section") String section, @RequestParam("kind")
            String kind, @RequestParam("order") String order,
     @RequestParam("page") int page, @RequestParam("limit") int limit) {
        ServerResponse<List<HashMap<String, Object>>> response = iPostService.getSectionPostWithPage(section, kind, order, page, limit);
        System.out.println(JSON.toJSONString(response));
        return response;
    }

}
