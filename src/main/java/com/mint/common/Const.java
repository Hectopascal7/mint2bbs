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

    // 操作状态常量
    public static final Integer OP_SUCCESS = 1;
    public static final Integer OP_ERROR = 0;

    // 用户状态常量
    public static final Integer USER_STATUS_NORMAL = 1;
    public static final Integer USER_STATUS_BAN = 0;
    public static final Integer USER_STATUS_LOGOUT = 2;

    // 操作类型常量
    public static final Integer OPERATION_TYPE_ADD = 1;
    public static final Integer OPERATION_TYPE_DELETE = 2;
    public static final Integer OPERATION_TYPE_UPDATE = 3;
    public static final Integer OPERATION_TYPE_SELECT = 4;
    public static final Integer OPERATION_TYPE_BAN = 5;
    public static final Integer OPERATION_TYPE_STICKY = 6;
    public static final Integer OPERATION_TYPE_BEST = 7;
    public static final Integer OPERATION_TYPE_REPLY = 8;
    public static final Integer OPERATION_TYPE_REPORT = 9;

    // 操作对象常量
    public static final Integer OPERATION_OBJECT_RESIDENT = 10;
    public static final Integer OPERATION_OBJECT_USER = 20;
    public static final Integer OPERATION_OBJECT_POST = 30;
    public static final Integer OPERATION_OBJECT_REPLY = 40;
    public static final Integer OPERATION_OBJECT_GOOD = 50;
    public static final Integer OPERATION_OBJECT_WEIBO = 60;
    public static final Integer OPERATION_OBJECT_LICENSE = 70;

    // 操作对象常量
    public static final Integer USER_SEX_MALE = 1;
    public static final Integer USER_SEX_FEMALE = 2;


    public interface Role {
        int ROLE_USER = 0; //普通用户
        int ROLE_ADMIN = 1;//管理员
    }


}
