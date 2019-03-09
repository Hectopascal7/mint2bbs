package com.mint.service;

import com.mint.common.ServerResponse;
import com.mint.pojo.Notice;
import com.mint.pojo.Post;
import com.mint.pojo.Topic;

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
    ServerResponse<String> post(Topic topic, String partid, HttpSession session);

    /**
     * @Description 获取社区公告板
     * @Return ServerResponse<List<Notice>>
     */
    ServerResponse<List<Notice>> getNoticeBoard();

    /**
     * @Description 获取置顶模块
     * @Return ServerResponse<List<Notice>>
     */
    ServerResponse<List<HashMap<String, String>>> getAllSticky();
}
