package com.mint.service;

import com.mint.common.ServerResponse;
import com.mint.pojo.Topic;
import com.mint.pojo.User;

import javax.servlet.http.HttpSession;

public interface ITopicService {

    ServerResponse<String> post(Topic topic, String partid, HttpSession session);
}
