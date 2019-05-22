package com.mint.controller.portal;

import com.alibaba.fastjson.JSON;
import com.mint.common.ServerResponse;
import com.mint.pojo.Good;
import com.mint.pojo.GoodWithBLOBs;
import com.mint.service.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * @Program: mint2bbs
 * @Description: 商品Action
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-07 20:55:31
 **/
@Controller
@RequestMapping("/good")
public class GoodController {

    @Autowired
    private IGoodService iGoodService;

    /**
     * @Description 获取商品
     * @Param loginid
     * @Param password
     * @Param session
     * @Return ServerResponse<User>
     */
    @RequestMapping(value = "getGoodListWithPage.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<HashMap<String, String>>> getGoodListWithPage(@RequestParam("isused") String isused, @RequestParam("order") String order, @RequestParam("page") int page) {
        ServerResponse<List<HashMap<String, String>>> response = iGoodService.getGoodListWithPage(isused, order, page);
        return response;
    }

    /**
     * @Description 发布商品
     * @Param loginid
     * @Param password
     * @Param session
     * @Return ServerResponse<User>
     */
    @RequestMapping(value = "publishAGood.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse publishAGood(String title, String isused, String ndegree, String price, String content, String cover, String picture, HttpSession httpSession) {
        return iGoodService.publishAGood(title, isused, ndegree, price, content, cover, picture,httpSession);
    }

    /**
     * @Description 上传商品图片
     * @Param goodPic
     * @Param httpServletRequest
     * @Return ServerResponse
     */
    @RequestMapping(value = "uploadGoodPic.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse uploadGoodPic(@RequestParam("goodPic") MultipartFile goodPic, HttpServletRequest httpServletRequest) {
        if (null != goodPic) {
            String url = iGoodService.uploadGoodCover(goodPic, httpServletRequest);
            System.out.println(JSON.toJSONString(ServerResponse.createBySuccess("封面上传成功！", url)));
            return ServerResponse.createBySuccess("封面上传成功！", url);
        } else {
            return ServerResponse.createByErrorMessage("封面上传失败！");
        }
    }


}
