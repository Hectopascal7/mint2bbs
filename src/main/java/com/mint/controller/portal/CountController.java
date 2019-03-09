package com.mint.controller.portal;

import com.mint.common.ServerResponse;
import com.mint.service.ICountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @Program: mint2bbs
 * @Description: 数量相关Action
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-09 21:41:27
 **/
@Controller
@RequestMapping("/count")
public class CountController {

    @Autowired
    ICountService iCountService;

    /**
     * @Description 用户注册
     * @Param user
     * @Param resident
     * @Param session
     * @Return ServerResponse<String>
     */
    @RequestMapping(value = "getActiveUser.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<HashMap<String, String>>> getActiveUser() {
        ServerResponse<List<HashMap<String, String>>> response = iCountService.getActiveUser();
        return response;
    }

}
