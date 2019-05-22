package com.mint.controller.portal;

import com.alibaba.fastjson.JSON;
import com.mint.common.Const;
import com.mint.common.ServerResponse;
import com.mint.pojo.Resident;
import com.mint.pojo.User;
import com.mint.service.IResidentService;
import com.mint.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Program: mint2bbs
 * @Description: 用户Action
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-05 15:43:26
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    // 自动注入-用户Service
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IResidentService iResidentService;

    /**
     * @Description 用户登录
     * @Param loginid
     * @Param password
     * @Param session
     * @Return ServerResponse<User>
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String loginid, String password, HttpSession session) {
        // 执行登录
        ServerResponse<User> response = iUserService.login(loginid, password);
        if (response.isSuccess()) {
            // 账号密码校验成功，将当前用户信息写入session，登录成功
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        System.out.println(JSON.toJSONString(response));
        return response;
    }

    /**
     * @Description 用户注册
     * @Param user
     * @Param resident
     * @Param session
     * @Return ServerResponse<String>
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(String loginid, String nickname, String password, String repassword, String uid, String name, String sex, String birthday, String building, String idcnum, String unit, String room, String phone, String role) {
        System.out.println(sex);
        System.out.println(role);
        // 后台再进行一次密码校验，确保两次输入的密码一致
        if (!password.equals(repassword)) {
            return ServerResponse.createByErrorMessage("两次输入的密码不一致，请重新输入！");
        } else {
            // 密码校验成功，执行注册
            ServerResponse<String> response = iUserService.register(loginid, nickname, password, uid, name, sex, birthday, building, idcnum, unit, room, phone, role);
            return response;
        }
    }

    /**
     * @Description 用户注册
     * @Param user
     * @Param resident
     * @Param session
     * @Return ServerResponse<String>
     */
    @RequestMapping(value = "userCenter.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUser(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess(user);
    }

    /**
     * @Description 获取用户主页资料
     * @Param user
     * @Param resident
     * @Param session
     * @Return ServerResponse<String>
     */
    @RequestMapping(value = "getIndexUserInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getIndexUserInfo(String uid) {
        User user = iUserService.getIndexUserInfo(uid);
        return ServerResponse.createBySuccess(user);
    }

    /**
     * @Description 获取用户主页资料
     * @Param user
     * @Param resident
     * @Param session
     * @Return ServerResponse<String>
     */
    @RequestMapping(value = "getUserByLicense.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Resident> getUserByLicense(String license) {
        return iResidentService.getUserByLicense(license);
    }


}
