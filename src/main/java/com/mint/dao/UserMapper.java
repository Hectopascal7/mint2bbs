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

    /**
     * @Description 查找登录id和密码对应记录数，判断用户信息是否匹配
     * @Param loginid
     * @Param password
     * @Return User
     */
    User checkUser(@Param("loginid") String loginid, @Param("password") String password);

    /**
     * @Description 查找登录id对应记录数，判断登录id是否存在
     * @Param loginid
     * @Return int
     */
    int checkLoginid(String loginid);

}