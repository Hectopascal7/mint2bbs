package com.mint.service.impl;

import com.mint.common.ServerResponse;
import com.mint.dao.CountMapper;
import com.mint.dao.ResidentMapper;
import com.mint.dao.UserMapper;
import com.mint.pojo.Count;
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
    @Autowired
    private CountMapper countMapper;

    /**
     * @Description 用户登录
     * @Param loginid
     * @Param password
     * @Return ServerResponse<User>
     */
    @Override
    public ServerResponse<User> login(String loginid, String password) {
        // 检查登录id是否存在
        int resultCount = userMapper.checkLoginid(loginid);
        // 登录id不存在
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("账号不存在,请检查后重新输入。");
        }
        // 登录id存在，校验账号密码是否匹配
        User user = userMapper.checkUser(loginid, password);
        // 登录id和密码匹配不到用户，登录失败
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误!");
        }
        // 登录id和密码匹配，保障账户安全，将用户实体密码置为空
        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功!", user);
    }

    /**
     * @Description 用户注册
     * @Param user
     * @Param resident
     * @Return ServerResponse<String>
     */
    @Override
    public ServerResponse<String> register(User user, Resident resident) {
        // 检查住户信息是否正确
        if (checkResident(resident)) {
            // 住户信息正确，检查登录id是否存在
            int resultCount = userMapper.checkLoginid(user.getLoginid());
            // 登录id不存在，可以进行注册
            if (resultCount == 0) {
                // 将用户信息存入用户信息表
                int result = userMapper.insertSelective(user);
                // 存储成功，注册成功
                if (result == 1) {
                    // 初始化用户贴子数、赞赏数等信息
                    Count count = new Count(user.getUid(), 0, 0, 0, 0);
                    countMapper.insert(count);
                    // 所有操作完毕，执行成功
                    return ServerResponse.createBySuccessMessage("注册成功！");
                // 存储失败，注册失败
                } else {
                    return ServerResponse.createByErrorMessage("注册失败！");
                }
            // 登录id已存在，无法进行注册
            } else {
                return ServerResponse.createByErrorMessage("注册账号已存在，请重新输入。");
            }
        } else {
            return ServerResponse.createByErrorMessage("用户信息校验错误！");
        }
    }

    /**
     * @Description 检查住户信息是否正确
     * @Param resident
     * @Return boolean
     */
    public boolean checkResident(Resident resident) {
        // 根据用户识别码查找用户
        Resident result = residentMapper.selectByPrimaryKey(resident.getUid());
        // 用户存在
        if (null != result && resident.equals(result)) {
            return true;
        // 用户不存在
        } else {
            return false;
        }
    }

}
