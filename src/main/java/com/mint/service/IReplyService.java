package com.mint.service;

import com.mint.common.ServerResponse;

import java.util.HashMap;
import java.util.List;

public interface IReplyService {
    ServerResponse<List<HashMap<String, String>>> getUserLatestReply(String uid);

    ServerResponse<List<HashMap<String, String>>> getHomeMoreReply(String uid);
}
