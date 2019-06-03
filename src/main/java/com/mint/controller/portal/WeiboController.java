package com.mint.controller.portal;

import com.mint.common.ServerResponse;
import com.mint.service.IWeiboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * @Program: mint2bbs
 * @Description: 微博Action
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-07 20:55:31
 **/
@Controller
@RequestMapping("/weibo")
public class WeiboController {

    @Autowired
    private IWeiboService iWeiboService;

    /**
     * @Description 获取微博
     * @Param page 获取页数
     * @Return ServerResponse<List<HashMap<String, String>>> 微博数据列表
     */
    @RequestMapping(value = "getWeiboWithPage.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<HashMap<String, String>>> getWeiboWithPage(String page) {
        return iWeiboService.getWeiboWithPage(page);
    }

    /**
     * @Description 发布微博
     * @Param content 微博内容
     * * @Param httpSession 用户当前Session
     * @Return ServerResponse 无数据服务器响应
     */
    @RequestMapping(value = "publishAWeibo.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse publishAWeibo(String content, HttpSession httpSession) {
        return iWeiboService.publishAWeibo(content, httpSession);
    }

}
