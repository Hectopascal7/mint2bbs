package com.mint.controller.portal;

import com.alibaba.fastjson.JSON;
import com.mint.common.ServerResponse;
import com.mint.pojo.Checkin;
import com.mint.service.ICheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/checkin")
public class CheckinController {
    @Autowired
    private ICheckinService iCheckinService;


    /**
     * @Description 签到
     * @Param httpSession
     * @Return ServerResponse
     */
    @RequestMapping(value = "checkin.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse checkin(HttpSession httpSession) {
        return iCheckinService.checkin(httpSession);
    }

    /**
     * @Description 获取签到信息
     * @Param httpSession
     * @Return ServerResponse
     */
    @RequestMapping(value = "getUserCheckinInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Checkin> getUserCheckinInfo(HttpSession httpSession) {
        System.out.println(JSON.toJSONString(iCheckinService.getUserCheckinInfo(httpSession)));
        return iCheckinService.getUserCheckinInfo(httpSession);
    }

}
