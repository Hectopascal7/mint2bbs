package com.mint.service;

import com.mint.common.ServerResponse;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

public interface IWeiboService {

    /**
     * @Description 获取微博
     * @Param page 获取页数
     * @Return ServerResponse<List<HashMap<String, String>>> 微博数据列表
     */
    ServerResponse<List<HashMap<String, String>>> getWeiboWithPage(String page);

    /**
     * @Description 发布微博
     * @Param content 微博内容
     * * @Param httpSession 用户当前Session
     * @Return ServerResponse 无数据服务器响应
     */
    ServerResponse publishAWeibo(String content, HttpSession httpSession);
}
