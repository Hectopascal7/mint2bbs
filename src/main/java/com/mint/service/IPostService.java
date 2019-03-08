package com.mint.service;

import com.mint.common.ServerResponse;
import com.mint.pojo.Topic;

import javax.servlet.http.HttpSession;

public interface IPostService {

    /**
     * @Description 发帖
     * @Param loginid
     * @Param password
     * @Param session
     * @Return ServerResponse<User>
     */
    ServerResponse<String> post(Topic topic, String partid, HttpSession session);
}
