package com.mint.service.impl;

import com.mint.common.ServerResponse;
import com.mint.dao.ResidentMapper;
import com.mint.dao.UserMapper;
import com.mint.pojo.Resident;
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
    @Autowired
    private ResidentMapper residentMapper;

    // 登录
    @Override
    public ServerResponse<User> login(String loginid, String password) {
        // 检查登录id是否存在
        int resultCount = userMapper.checkLoginid(loginid);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("账号不存在,请检查后重新输入。");
        }
        User user = userMapper.checkUser(loginid, password);
        System.out.println(user);
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误!");
        }
        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功!", user);
    }

    // 注册
    @Override
    public ServerResponse<String> register(User user, Resident resident) {
        // 检查住户信息是否正确
        if(checkResident(resident)){
            // 住户信息正确，检查登录id是否存在
            int resultCount = userMapper.checkLoginid(user.getLoginid());
            if (resultCount == 0) {
                int result = userMapper.insertSelective(user);
                return ServerResponse.createBySuccessMessage("注册成功！");
            } else {
                return ServerResponse.createByErrorMessage("注册账号已存在，请重新输入。");
            }
        }else{
            return ServerResponse.createByErrorMessage("用户信息校验错误！");
        }
    }

    // 检查住户信息是否正确
    public boolean checkResident(Resident resident) {
        Resident result = residentMapper.selectByPrimaryKey(resident.getUid());
        if (null != result && resident.equals(result)) {
            return true;
        } else {
            return false;
        }
    }

}
