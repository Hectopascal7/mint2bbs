package com.mint.service;

import com.mint.common.ServerResponse;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

public interface IMessageService {
    ServerResponse<List<HashMap<String, String>>> getUserMessage(HttpSession httpSession);

    ServerResponse report(Integer mtype, String oid, Integer otype, HttpSession httpSession);

    ServerResponse updateMessageRead(String mid,HttpSession httpSession);

    ServerResponse deleteMessage(String mid, HttpSession httpSession);
}
