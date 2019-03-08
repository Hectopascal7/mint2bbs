package com.mint.service.impl;

import com.mint.common.Const;
import com.mint.common.ServerResponse;
import com.mint.dao.TopicMapper;
import com.mint.pojo.Topic;
import com.mint.pojo.User;
import com.mint.service.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * @Program: mint2bbs
 * @Description: ITopicService的实现类
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-07 20:56:41
 **/
@Service("iTopicService")
public class TopicServiceImpl implements ITopicService {

    @Autowired
    private TopicMapper topicMapper;

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
}
