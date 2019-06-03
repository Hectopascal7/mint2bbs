package com.mint.service.impl;

import com.mint.common.Const;
import com.mint.common.ServerResponse;
import com.mint.dao.PraiseMapper;
import com.mint.pojo.Praise;
import com.mint.pojo.User;
import com.mint.service.IPraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

/**
 * @Program: mint2bbs
 * @Description: 点赞相关Service实现
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-15 12:38:37
 **/
@Service("iPraiseService")
public class PraiseServiceImpl implements IPraiseService {

    @Autowired
    PraiseMapper praiseMapper;

    @Override
    public ServerResponse<String> praise(String iid, int itype, String isid, HttpSession session) {
        String pid = UUID.randomUUID().toString();
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        Date ptime = new Date(System.currentTimeMillis());
        System.out.println(itype);
        Praise praise = new Praise(pid, iid, itype, isid, user.getUid(), ptime);
        int result = praiseMapper.insert(praise);
        if (result == 1) {
            return ServerResponse.createBySuccess("点赞成功！", pid);
        } else {
            return ServerResponse.createByError();
        }
    }

    @Override
    public ServerResponse<String> cancelPraise(String pid) {
        Praise praise = praiseMapper.selectByPrimaryKey(pid);
        int result = praiseMapper.deleteByPrimaryKey(pid);
        System.out.println(pid);
        if (result == 1) {
            return ServerResponse.createBySuccess("取消点赞成功！", praise.getIid());
        } else {
            return ServerResponse.createByErrorMessage("取消点赞失败！");
        }
    }
}
