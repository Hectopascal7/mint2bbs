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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public ServerResponse<String> register(String loginid, String nickname, String password, String repassword, String uid, String name, String sex, String birthday, String building, String idcnum, String unit, String floor, String room, String phone, String role) {
        System.out.println(sex);
        System.out.println(role);
        // 后台再进行一次密码校验，确保两次输入的密码一致
        if (!password.equals(repassword)) {
            return ServerResponse.createByErrorMessage("两次输入的密码不一致，请重新输入！");
        } else {
            // 密码校验成功，执行注册
            ServerResponse<String> response = iUserService.register(loginid, nickname, password, uid, name, sex, birthday, building, unit, floor, room, idcnum, phone, role);
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

    /**
     * @Description 获取用户主页资料
     * @Param now_password
     * @Param new_password
     * @Param re_new_password
     * @Return ServerResponse<String>
     */
    @RequestMapping(value = "updatePassword.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updatePassword(String now_password, String new_password, String re_new_password, HttpSession httpSession) {
        return iUserService.updatePassword(now_password, new_password, re_new_password, httpSession);
    }

    /**
     * @Description 获取用户主页资料
     * @Param now_password
     * @Param new_password
     * @Param re_new_password
     * @Return ServerResponse<String>
     */
    @RequestMapping(value = "updateUserInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateUserInfo(String nickname, String email, String license, String signature, HttpSession httpSession) {
        return iUserService.updateUserInfo(nickname, email, license, signature, httpSession);
    }

    /**
     * @Description 上传商品图片
     * @Param goodPic
     * @Param httpServletRequest
     * @Return ServerResponse
     */
    @RequestMapping(value = "uploadProfile.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> uploadProfile(@RequestParam("profile") MultipartFile profile, HttpServletRequest httpServletRequest) {
        if (null != profile) {
            return iUserService.uploadProfile(profile, httpServletRequest);
        } else {
            return ServerResponse.createByErrorMessage("头像上传失败！");
        }
    }

    /**
     * @Description 上传商品图片
     * @Param goodPic
     * @Param httpServletRequest
     * @Return ServerResponse
     */
    @RequestMapping(value = "updateUserProfile.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateUserProfile(@RequestParam("profile") String profile, HttpSession httpSession) {
        System.out.println("还可以啊");
        return iUserService.updateUserProfile(profile, httpSession);
    }

    /**
     * @Description 用户注册
     * @Param user
     * @Param resident
     * @Param session
     * @Return ServerResponse<String>
     */
    @RequestMapping(value = "getUidByNickname.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> getUidByNickname(String nickname) {
        return iUserService.getUidByNickname(nickname);
    }

}
