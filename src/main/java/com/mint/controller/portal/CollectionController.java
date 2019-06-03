package com.mint.controller.portal;

import com.mint.common.ServerResponse;
import com.mint.service.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

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
    public ServerResponse<String> collect(String iid, int itype, String isid, HttpSession session) {
        ServerResponse<String> response = iCollectionService.collect(iid, itype, isid, session);
        return response;
    }

    /**
     * @Description 取消收藏
     * @Param user
     * @Param resident
     * @Param session
     * @Return ServerResponse<String>
     */
    @RequestMapping(value = "cancelCollect.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse cancelCollect(String cid) {
        return iCollectionService.cancelCollect(cid);
    }

    /**
     * @Description 收藏
     * @Param user
     * @Param resident
     * @Param session
     * @Return ServerResponse<String>
     */
    @RequestMapping(value = "getMyCollectionCount.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Integer> getMyCollectionCount(HttpSession session) {
        return iCollectionService.getMyCollectionCount(session);
    }


    /**
     * @Description 收藏
     * @Param user
     * @Param resident
     * @Param session
     * @Return ServerResponse<String>
     */
    @RequestMapping(value = "getMyCollectionWithPage.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<HashMap<String, String>>> getMyCollectionWithPage(Integer page, Integer limit, HttpSession session) {
        return iCollectionService.getMyCollectionWithPage(page, limit, session);
    }

}
