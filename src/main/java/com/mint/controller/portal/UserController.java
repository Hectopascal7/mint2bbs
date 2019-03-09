package com.mint.controller.portal;

import com.mint.common.Const;
import com.mint.common.ServerResponse;
import com.mint.pojo.Resident;
import com.mint.pojo.User;
import com.mint.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

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
    public ServerResponse<String> register(User user, Resident resident, String repassword, HttpSession session) {
        // 后台再进行一次密码校验，确保两次输入的密码一致
        if (!user.getPassword().equals(repassword)) {
            return ServerResponse.createByErrorMessage("两次输入的密码不一致，请重新输入！");
        } else {
            // 密码校验成功，执行注册
            ServerResponse<String> response = iUserService.register(user, resident);
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


}
