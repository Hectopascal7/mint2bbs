package com.mint.service.impl;

import com.mint.common.ServerResponse;
import com.mint.dao.CountMapper;
import com.mint.dao.UserMapper;
import com.mint.pojo.Count;
import com.mint.pojo.User;
import com.mint.service.ICountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Program: mint2bbs
 * @Description: 数量Service实现类
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-09 21:43:28
 **/
@Service("iCountService")
public class CountServiceImpl implements ICountService {

    @Autowired
    CountMapper countMapper;
    @Autowired
    UserMapper userMapper;

    /**
     * @Description 获取活跃用户榜单
     * @Return ServerResponse<String>
     */
    @Override
    public ServerResponse<List<HashMap<String, String>>> getActiveUser() {
        List<Count> list = countMapper.getActiveUser();
        List<HashMap<String, String>> rlist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Count count = list.get(i);
            int tcount = count.getTcount() + count.getPcount() + count.getRcount();
            User user = userMapper.selectByPrimaryKey(count.getUid());
            HashMap<String, String> map = new HashMap<>();
            map.put("uid", user.getUid());
            map.put("count", String.valueOf(tcount));
            map.put("nickname", user.getNickname());
            map.put("profile", user.getProfile());
            rlist.add(map);
        }
        return ServerResponse.createBySuccess(rlist);
    }

}
