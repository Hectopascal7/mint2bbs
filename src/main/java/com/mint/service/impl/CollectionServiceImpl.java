package com.mint.service.impl;

import com.mint.common.Const;
import com.mint.common.ServerResponse;
import com.mint.dao.CollectionMapper;
import com.mint.dao.GoodMapper;
import com.mint.dao.PostMapper;
import com.mint.dao.SectionMapper;
import com.mint.pojo.*;
import com.mint.pojo.Collection;
import com.mint.service.ICollectionService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Program: mint2bbs
 * @Description: 收藏相关Service实现
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-15 13:35:10
 **/
@Service("iCollectionService")
public class CollectionServiceImpl implements ICollectionService {

    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private GoodMapper goodMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private SectionMapper sectionMapper;

    @Override
    public ServerResponse<String> collect(String iid, int itype, String isid, HttpSession session) {
        System.out.println(itype);
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        String cid = UUID.randomUUID().toString();
        Collection collection = new Collection(cid, iid, itype, isid, user.getUid(), new Date(System.currentTimeMillis()));
        int result = collectionMapper.insert(collection);
        if (result == 1) {
            return ServerResponse.createBySuccess("收藏成功！", cid);
        } else {
            return ServerResponse.createByErrorMessage("收藏失败！");
        }
    }

    @Override
    public ServerResponse<String> cancelCollect(String cid) {
        Collection collection = collectionMapper.selectByPrimaryKey(cid);
        int result = collectionMapper.deleteByPrimaryKey(cid);
        if (result == 1) {
            return ServerResponse.createBySuccess("取消收藏成功！", collection.getIid());
        } else {
            return ServerResponse.createByErrorMessage("取消收藏失败！");
        }
    }

    @Override
    public ServerResponse<Integer> getMyCollectionCount(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        String uid = user.getUid();
        Integer count = collectionMapper.getMyCollectionCount(uid);
        return ServerResponse.createBySuccess(count);
    }

    @Override
    public ServerResponse<List<HashMap<String, String>>> getMyCollectionWithPage(Integer page, Integer limit, HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        String uid = user.getUid();
        Integer start = (page - 1) * limit;
        List<Collection> list = collectionMapper.getMyCollectionWithPage(start, limit, uid);
        List<HashMap<String, String>> collectionList = new ArrayList<>();
        for (Collection c : list) {
            HashMap<String, String> map = new HashMap<>();
            map.put("iid", c.getIid());
            map.put("isid", c.getIsid());
            String tb_name = sectionMapper.selectByPrimaryKey(c.getIsid()).getTbname();
            Post post = postMapper.getPostByTid(c.getIid(), tb_name);
            map.put("title", post.getTitle());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            map.put("ctime", sdf.format(c.getCtime()));
            collectionList.add(map);
        }
        return ServerResponse.createBySuccess(collectionList);
    }
}
