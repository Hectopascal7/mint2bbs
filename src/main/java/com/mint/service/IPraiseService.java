package com.mint.service;

import com.mint.common.ServerResponse;

import javax.servlet.http.HttpSession;

public interface IPraiseService {
    ServerResponse<String> praise(String section, HttpSession session);
}
