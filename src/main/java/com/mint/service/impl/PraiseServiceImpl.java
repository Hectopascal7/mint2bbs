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
    public ServerResponse<String> praise(String section, HttpSession session) {
        String pid = UUID.randomUUID().toString();
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        Praise praise = new Praise(pid, null, section, user.getUid(), new Date(System.currentTimeMillis()));
        int result = praiseMapper.insert(praise);
        if (result == 1) {
            return ServerResponse.createBySuccessMessage("点赞成功！");
        } else {
            return ServerResponse.createByErrorMessage("点赞失败！");
        }
    }
}
