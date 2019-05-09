package com.mint.service;

import com.mint.common.ServerResponse;
import com.mint.pojo.Resident;
import com.mint.pojo.User;

import java.util.HashMap;
import java.util.List;

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

    /**
     * @Description 用户注册
     * @Param user
     * @Param resident
     * @Return ServerResponse<String>
     */
    ServerResponse<String> register(User user, Resident resident);

    User getIndexUserInfo(String uid);
}
