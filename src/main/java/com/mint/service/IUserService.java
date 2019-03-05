package com.mint.service;

import com.mint.common.ServerResponse;
import com.mint.pojo.User;

/**
 * @Program: mint2bbs
 * @Description: 用户Service接口
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-05 15:43:26
 **/
public interface IUserService {

    // 登录
    ServerResponse<User> login(String loginid, String password);
}
