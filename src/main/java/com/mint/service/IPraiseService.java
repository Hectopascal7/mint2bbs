package com.mint.service;

import com.mint.common.ServerResponse;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public interface IPraiseService {

    ServerResponse<String> praise(String iid, int itype, String isid, HttpSession session);

    ServerResponse<HashMap<String, String>> cancelPraise(String pid);
}
