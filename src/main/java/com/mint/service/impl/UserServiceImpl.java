package com.mint.service.impl;

import com.mint.common.ServerResponse;
import com.mint.dao.UserMapper;
import com.mint.pojo.User;
import com.mint.service.IUserService;
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

    @Override
    public ServerResponse<User> login(String username, String password) {
//        int resultCount = userMapper.checkUsername(username);
//        if(resultCount == 0 ){
//            return ServerResponse.createByErrorMessage("用户名不存在");
//        }
//
//        String md5Password = MD5Util.MD5EncodeUtf8(password);
//        User user  = userMapper.selectLogin(username,md5Password);
//        if(user == null){
//            return ServerResponse.createByErrorMessage("密码错误");
//        }

//        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功",null);
    }
}
