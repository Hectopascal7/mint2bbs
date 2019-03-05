package com.mint.dao;

import com.mint.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(String uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    // 检查登录ID是否存在
    int checkLoginid(String loginid);

    // 登录ID存在，检查登录ID与密码是否匹配
    User checkUser(@Param("loginid") String loginid, @Param("password") String password);
}