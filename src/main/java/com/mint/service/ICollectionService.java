package com.mint.service;

import com.mint.common.ServerResponse;

import javax.servlet.http.HttpSession;

public interface ICollectionService {

    ServerResponse<String> collect(String tid, HttpSession session);
}
