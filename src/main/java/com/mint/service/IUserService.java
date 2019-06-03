package com.mint.service;

import com.mint.common.ServerResponse;
import com.mint.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Program: mint2bbs
 * @Description: 用户Service接口
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-05 15:43:26
 **/
public interface IUserService {

    /**
     * @Description 用户登录
     * @Param loginid
     * @Param password
     * @Return ServerResponse<User>
     */
    ServerResponse<User> login(String loginid, String password);

    User getIndexUserInfo(String uid);

    ServerResponse<String> register(String loginid, String nickname, String password, String uid, String name, String sex, String birthday, String building, String unit, String floor, String room, String idcnum, String phone, String role);

    ServerResponse updatePassword(String now_password, String new_password, String re_new_password, HttpSession httpSession);

    ServerResponse updateUserInfo(String nickname, String email, String license, String signature, HttpSession httpSession);

    ServerResponse<String> uploadProfile(MultipartFile profile, HttpServletRequest httpServletRequest);

    ServerResponse updateUserProfile(String profile, HttpSession httpSession);
}
