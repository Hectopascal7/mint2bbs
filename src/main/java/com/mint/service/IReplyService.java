package com.mint.service;

import com.mint.common.ServerResponse;
import com.mint.pojo.Reply;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

public interface IReplyService {
    ServerResponse<List<HashMap<String, String>>> getUserLatestReply(String uid);

    ServerResponse<List<HashMap<String, String>>> getHomeMoreReply(String uid);

    ServerResponse reply(String tid, String content, String sid, HttpSession httpSession);

    ServerResponse<List<HashMap<String,String>>> getReplies(String tid, String sid,HttpSession httpSession);
}
