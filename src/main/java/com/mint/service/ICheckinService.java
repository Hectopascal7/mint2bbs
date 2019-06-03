package com.mint.service;

import com.mint.common.ServerResponse;
import com.mint.pojo.Checkin;

import javax.servlet.http.HttpSession;

public interface ICheckinService {
    ServerResponse checkin(HttpSession httpSession);

    ServerResponse<Checkin> getUserCheckinInfo(HttpSession httpSession);
}
