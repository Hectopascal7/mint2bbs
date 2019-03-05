package com.mint.service.impl;

import com.mint.common.ServerResponse;
import com.mint.dao.UserMapper;
import com.mint.pojo.User;
import com.mint.service.IUserService;
import com.mint.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Program: mint2bbs
 * @Description: IUserService的实现类
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-05 15:47:56
 **/
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    // 登录
    @Override
    public ServerResponse<User> login(String loginid, String password) {
        // 检查登录id是否存在
        int resultCount = userMapper.checkLoginid(loginid);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("账号不存在,请检查后重新输入。");
        }
        User user  = userMapper.checkUser(loginid,password);
        System.out.println(user);
        if(user == null){
            return ServerResponse.createByErrorMessage("密码错误!");
        }
        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功!", user);
    }
}
