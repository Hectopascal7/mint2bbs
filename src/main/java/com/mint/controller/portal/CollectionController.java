package com.mint.controller.portal;

import com.mint.common.ServerResponse;
import com.mint.service.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Program: mint2bbs
 * @Description: 收藏相关Action
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-15 13:33:29
 **/
@Controller
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    ICollectionService iCollectionService;

    /**
     * @Description 收藏
     * @Param user
     * @Param resident
     * @Param session
     * @Return ServerResponse<String>
     */
    @RequestMapping(value = "collect.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> collect(String tid, HttpSession session) {
        ServerResponse<String> response = iCollectionService.collect(tid,session);
        return response;
    }


}
