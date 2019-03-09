package com.mint.controller.portal;

import com.mint.common.ServerResponse;
import com.mint.pojo.Notice;
import com.mint.pojo.Topic;
import com.mint.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
     * @Return ServerResponse<List<Notice>>
     */
    @RequestMapping(value = "getNoticeBoard.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<Notice>> getNoticeBoard() {
        System.out.println(22222222);
        ServerResponse<List<Notice>> response= iPostService.getNoticeBoard();
        return response;
    }
}
