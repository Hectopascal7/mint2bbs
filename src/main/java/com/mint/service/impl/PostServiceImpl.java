package com.mint.service.impl;

import com.alibaba.fastjson.JSON;
import com.mint.common.Const;
import com.mint.common.ServerResponse;
import com.mint.dao.*;
import com.mint.pojo.Notice;
import com.mint.pojo.Post;
import com.mint.pojo.Topic;
import com.mint.pojo.User;
import com.mint.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Program: mint2bbs
 * @Description: ITopicService的实现类
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-07 20:56:41
 **/
@Service("iPostService")
public class PostServiceImpl implements IPostService {

    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SectionMapper sectionMapper;

    /**
     * @Description 发帖
     * @Param loginid
     * @Param password
     * @Param session
     * @Return ServerResponse<User>
     */
    @Override
    public ServerResponse<String> post(Topic topic, String partid, HttpSession session) {
        if ("001".equals(partid)) {
            User user = (User) session.getAttribute(Const.CURRENT_USER);
            topic.setUid(user.getUid());
            topic.setTid(UUID.randomUUID().toString());
            System.out.println(topic.getTid());
        }
        System.out.println(topic);
        topicMapper.insertSelective(topic);
        return ServerResponse.createBySuccessMessage("12");
    }

    /**
     * @Description 获取社区公告板
     * @Return ServerResponse<List < Notice>>
     */
    @Override
    public ServerResponse<List<Notice>> getNoticeBoard() {
        return ServerResponse.createBySuccess(noticeMapper.getNoticeBoard());
    }

    /**
     * @Description 获取置顶模块
     * @Return ServerResponse<List < Notice>>
     */
    @Override
    public ServerResponse<List<HashMap<String, String>>> getAllSticky() {
        List<Post> list = postMapper.getAllSticky();
        List<HashMap<String, String>> postList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Post post = list.get(i);
            String nickname = userMapper.getNicknameByUid(post.getUid());
            int role = userMapper.getRoleByUid(post.getUid());
            String sname = sectionMapper.getSnameBySid(post.getSid());
//            User user=userMapper.selectByPrimaryKey();
            HashMap<String, String> map = new HashMap<>();
            map.put("nickname", nickname);
            map.put("sname", sname);
            map.put("role", String.valueOf(role));
            map.put("post", JSON.toJSONString(post));
            postList.add(map);
        }
        return ServerResponse.createBySuccess(postList);
    }
}
