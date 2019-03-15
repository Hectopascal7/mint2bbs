package com.mint.controller.portal;

import com.mint.common.ServerResponse;
import com.mint.service.IPraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Program: mint2bbs
 * @Description: 点赞相关Action
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-15 12:35:48
 **/
@Controller
@RequestMapping("/praise")
public class PraiseController {

    @Autowired
    IPraiseService iPraiseService;

    /**
     * @Description 点赞
     * @Param loginid
     * @Param password
     * @Param session
     * @Return ServerResponse<User>
     */
    @RequestMapping(value = "praise.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> praise(String section, HttpSession session) {
        ServerResponse<String> response = iPraiseService.praise(section, session);
        return response;
    }
}
