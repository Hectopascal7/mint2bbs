package com.mint.controller.portal;

import com.alibaba.fastjson.JSON;
import com.mint.common.ServerResponse;
import com.mint.service.ICountService;
import com.mint.service.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @Program: mint2bbs
 * @Description: 数量相关Action
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-09 21:41:27
 **/
@Controller
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private IReplyService iReplyService;

    /**
     * @Description 获取用户最近回复
     * @Param uid
     * @Return ServerResponse<String>
     */
    @RequestMapping(value = "getUserLatestReply.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<HashMap<String, String>>> getUserLatestReply(String uid) {
        ServerResponse<List<HashMap<String, String>>> response = iReplyService.getUserLatestReply(uid);
        System.out.println(JSON.toJSONString(response));
        return response;
    }

    /**
     * @Description 获取帖子回复
     * @Param tid
     * @Param section
     * @Return ServerResponse<HashMap < String, Object>>
     */
    @RequestMapping(value = "getHomeMoreReply.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<HashMap<String, String>>> getHomeMoreReply(String uid) {
        ServerResponse<List<HashMap<String, String>>> response = iReplyService.getHomeMoreReply(uid);
        return response;
    }

}