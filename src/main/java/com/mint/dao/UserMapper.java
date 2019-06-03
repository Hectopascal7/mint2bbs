package com.mint.dao;

import com.mint.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(String uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    int checkLoginid(String loginid);

    User checkUser(@Param("loginid") String loginid, @Param("password") String password);

    String getNicknameByUid(String uid);

    User getUserOnPostDetail(String uid);

    int getRoleByUid(String uid);

    User getInfoByUid(String uid);

    Integer checkNowPassword(@Param("now_password") String now_password, @Param("uid") String uid);

    Integer updatePassword(@Param("new_password") String new_password, @Param("uid") String uid);

    Integer checkNickname(@Param("nickname") String nickname, @Param("uid") String uid);

    Integer updateUserInfo(@Param("uid") String uid, @Param("nickname") String nickname, @Param("email") String email, @Param("license") String license, @Param("signature") String signature);

    Integer updateUserProfile(@Param("profile") String profile, @Param("uid") String uid);
}