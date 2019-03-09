package com.mint.dao;

import com.mint.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(String loginid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String loginid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkLoginid(String loginid);

    User checkUser(@Param("loginid") String loginid, @Param("password") String password);

    String getNicknameByUid(String uid);

    int getRoleByUid(String uid);
}