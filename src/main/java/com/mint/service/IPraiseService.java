package com.mint.service;

import com.mint.common.ServerResponse;

import javax.servlet.http.HttpSession;

public interface IPraiseService {

    ServerResponse<String> praise(String iid, int itype,String isid, HttpSession session);

    ServerResponse cancelPraise(String pid);
}
