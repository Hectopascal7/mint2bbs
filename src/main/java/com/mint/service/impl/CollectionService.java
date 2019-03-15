package com.mint.service.impl;

import com.mint.common.Const;
import com.mint.common.ServerResponse;
import com.mint.dao.CollectionMapper;
import com.mint.pojo.Collection;
import com.mint.pojo.User;
import com.mint.service.ICollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

/**
 * @Program: mint2bbs
 * @Description: 收藏相关Service实现
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-15 13:35:10
 **/
@Service("iCollectionService")
public class CollectionService implements ICollectionService {

    @Autowired
    CollectionMapper collectionMapper;

    @Override
    public ServerResponse<String> collect(String tid, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        String cid = UUID.randomUUID().toString();
        Collection collection = new Collection(cid, tid, user.getUid(), new Date(System.currentTimeMillis()));
        int result = collectionMapper.insert(collection);
        if (result == 1) {
            return ServerResponse.createBySuccessMessage("收藏成功！");
        } else {
            return ServerResponse.createByErrorMessage("收藏失败！");
        }
    }
}
