package com.mint.controller.portal;

import com.mint.common.ServerResponse;
import com.mint.pojo.Section;
import com.mint.service.ISectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Program: mint2bbs
 * @Description: 论坛频道Action
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-09 09:52:49
 **/
@Controller
@RequestMapping("/section")
public class SectionController {

    @Autowired
    ISectionService iSectionService;

    /**
     * @Description 获取论坛板块
     * @Return ServerResponse<String>
     */
    @RequestMapping(value = "getSection.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<Section>> getAllSection() {
        ServerResponse<List<Section>> response = iSectionService.getAllSection();
        return response;
    }

}
