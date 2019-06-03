package com.mint.service.impl;

import com.mint.common.Const;
import com.mint.common.ServerResponse;
import com.mint.dao.UserMapper;
import com.mint.dao.WeiboMapper;
import com.mint.pojo.User;
import com.mint.pojo.Weibo;
import com.mint.service.IWeiboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service("iWeiboService")
public class WeiboServiceImpl implements IWeiboService {

    @Autowired
    private WeiboMapper weiboMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * @Description 获取微博
     * @Param page 获取页数
     * @Return ServerResponse<List < HashMap < String, String>>> 微博数据列表
     */
    @Override
    public ServerResponse<List<HashMap<String, String>>> getWeiboWithPage(String page) {
        System.out.println("page" + page);
        Integer limit = Integer.parseInt(page) * 10;
        System.out.println(limit);
        List<Weibo> list = weiboMapper.getWeiboWithPage(limit);
        List<HashMap<String, String>> weiboList = new ArrayList<>();
        for (Weibo weibo : list) {
            User user = userMapper.selectByPrimaryKey(weibo.getUid());
            HashMap<String, String> map = new HashMap<>();
            map.put("content", weibo.getContent());
            map.put("sex", user.getSex().toString());
            weiboList.add(map);
        }
        return ServerResponse.createBySuccess(weiboList);
    }

    /**
     * @Description 发布微博
     * @Param content 微博内容
     * * @Param httpSession 用户当前Session
     * @Return ServerResponse 无数据服务器响应
     */
    @Override
    public ServerResponse publishAWeibo(String content, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        String tid = UUID.randomUUID().toString();
        Date ptime = new Date(System.currentTimeMillis());
        Weibo weibo = new Weibo(tid, user.getUid(), ptime, content);
        Integer result = weiboMapper.insert(weibo);
        if (Const.OP_SUCCESS == result) {
            return ServerResponse.createBySuccessMessage("发布微博成功！");
        }
        return ServerResponse.createByErrorMessage("发布微博失败！");
    }
}
