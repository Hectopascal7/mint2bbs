package com.mint.controller.portal;

import com.mint.common.ServerResponse;
import com.mint.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private IMessageService iMessageService;

    @RequestMapping(value = "getUserMessage.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<HashMap<String, String>>> getUserMessage(HttpSession httpSession) {
        return iMessageService.getUserMessage(httpSession);
    }

    @RequestMapping(value = "report.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse report(Integer mtype, String oid, Integer otype, HttpSession httpSession) {
        return iMessageService.report(mtype,oid,otype,httpSession);
    }
}
