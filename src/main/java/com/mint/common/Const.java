package com.mint.common;

/**
 * @Program: mint2bbs
 * @Description: 常量数据
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-05 16:46:35
 **/
public class Const {
    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    public interface Role {
        int ROLE_CUSTOMER = 0; //普通用户
        int ROLE_ADMIN = 1;//管理员
    }

}
