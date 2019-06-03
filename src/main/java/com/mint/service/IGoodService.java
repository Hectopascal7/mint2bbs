package com.mint.service;

import com.mint.common.ServerResponse;
import com.mint.pojo.Good;
import com.mint.pojo.GoodWithBLOBs;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

public interface IGoodService {

    ServerResponse uploadGoodPic(MultipartFile cover, HttpServletRequest httpServletRequest);

    ServerResponse publishAGood(String title, String isused, String ndegree, String price, String content, String cover, String picture, HttpSession httpSession);

    ServerResponse<List<HashMap<String, String>>> getGoodListWithPage(String isused, String order, int page);

    ServerResponse<List<Good>> getMyGoodList(HttpSession httpSession);

    ServerResponse updateGoodPrice(String gid, String price);

    ServerResponse setGoodIsSaled(String gid);

    ServerResponse<HashMap<String, String>> getGoodInfo(String gid, HttpSession httpSession);

    ServerResponse<List<HashMap<String, String>>> getHotGood();
}
