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
        ServerResponse<User> response = iUserService.login(loginid, password);
        if (response.isSuccess()) {
            System.out.println("sc");
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        User user1 = (User) session.getAttribute(Const.CURRENT_USER);
        System.out.println(user1.getUid());
        return response;
    }

    /**
     * @Description 用户注册
     * @Param loginid
     * @Param password
     * @Param session
     * @Return ServerResponse<User>
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user, Resident resident, HttpSession session) {
        ServerResponse<String> response = iUserService.register(user, resident);
        System.out.println(user);
        System.out.println(resident);
        if (response.isSuccess()) {

            session.setAttribute(Const.CURRENT_USER, response.getData());
        }

        return response;
    }

}
