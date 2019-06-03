package com.mint.service;

import com.mint.common.ServerResponse;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

public interface ICollectionService {

    ServerResponse<String> collect(String iid, int itype, String isid, HttpSession session);

    ServerResponse cancelCollect(String pid);

    ServerResponse<Integer> getMyCollectionCount(HttpSession session);

    ServerResponse<List<HashMap<String, String>>> getMyCollectionWithPage(Integer page, Integer limit, HttpSession session);
}
