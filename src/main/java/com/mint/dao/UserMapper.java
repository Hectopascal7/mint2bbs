package com.mint.dao;

import com.mint.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(String loginid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String loginid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkLoginid(String loginid);

    User checkUser(String loginid, String password);
}