package com.mint.service.impl;

import com.alibaba.fastjson.JSON;
import com.mint.common.Const;
import com.mint.common.ServerResponse;
import com.mint.dao.*;
import com.mint.pojo.*;
import com.mint.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.parser.Entity;
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
    private NewsMapper newsMapper;
    @Autowired
    private AdviceMapper adviceMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private GuideMapper guideMapper;
    @Autowired
    private SectionMapper sectionMapper;
    @Autowired
    private ReplyMapper replyMapper;

    /**
     * @param sid
     * @param title
     * @param content
     * @param session
     * @Description 发帖
     * @Return ServerResponse<User>
     */
    @Override
    public ServerResponse<String> post(String sid, String title, String content, HttpSession session) {
        int result = 0;
        String tid = UUID.randomUUID().toString();
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        String uid = user.getUid();
        switch (sid) {
            case "topic":
                Topic topic = new Topic(tid, uid, sid, title, new Date(System.currentTimeMillis()), content);
                result = topicMapper.insertSelective(topic);
                break;
            case "activity":
                Activity activity = new Activity(tid, uid, sid, title, new Date(System.currentTimeMillis()), content);
                result = activityMapper.insertSelective(activity);
                break;
            case "notice":
                Notice notice = new Notice(tid, uid, sid, title, new Date(System.currentTimeMillis()), content);
                result = noticeMapper.insertSelective(notice);
                break;
            case "news":
                News news = new News(tid, uid, sid, title, new Date(System.currentTimeMillis()), content);
                result = newsMapper.insertSelective(news);
                break;
            case "guide":
                Guide guide = new Guide(tid, uid, sid, title, new Date(System.currentTimeMillis()), content);
                result = guideMapper.insertSelective(guide);
                break;
            case "advice":
                Advice advice = new Advice(tid, uid, sid, title, new Date(System.currentTimeMillis()), content);
                result = adviceMapper.insertSelective(advice);
                break;
        }
        if (result == 1) {
            return ServerResponse.createBySuccessMessage("发帖成功！");
        } else {
            return ServerResponse.createByErrorMessage("发帖失败！");
        }
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
    public ServerResponse<List<PostEntity>> getAllSticky() {
        List<Post> list = postMapper.getAllSticky();
        List<PostEntity> t_list = matchUser(list);
        return ServerResponse.createBySuccess(t_list);
    }

    /**
     * @Description 帖子列表Post实体匹配到用户，生成前端对应帖子实体列表List<PostEntity>
     * @Return ServerResponse<List < Notice>>
     */
    public List<PostEntity> matchUser(List<Post> list) {
        List<PostEntity> t_list = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Post post = list.get(i);
            User user = userMapper.getInfoByUid(post.getUid());
            PostEntity entity = new PostEntity(post.getTid(), post.getSid(), post.getUid(), user.getNickname(), sectionMapper.getSnameBySid(post.getSid()), post.getTitle(), post.getPtime(), post.getAcount(), post.getRcount(), post.getIsbest(), post.getIssticky(), post.getPcount(), user.getRole(), user.getUlevel(), user.getProfile());
            t_list.add(entity);
        }
        return t_list;
    }

    /**
     * @Description 获取热门帖子
     * @Return ServerResponse<List < Notice>>
     */
    @Override
    public ServerResponse<List<Post>> getHotPost() {
        List<Post> list = postMapper.getHotPost();
        return ServerResponse.createBySuccess(list);
    }

    /**
     * @param page
     * @Description 获取帖子列表
     * @Return ServerResponse<List < Notice>>
     */
    @Override
    public ServerResponse<List<PostEntity>> getPostByPtime(int page, String kind, String order) {
        List<Post> list = postMapper.getPostByPtime(kind, order, page * 10);
        List<PostEntity> t_list = matchUser(list);
        return ServerResponse.createBySuccess(t_list);
    }

    /**
     * @param sid
     * @Description 获取主页热门帖子
     * @Param sid
     * @Return ServerResponse<List < Notice>>
     */
    @Override
    public ServerResponse<List<Post>> getSectionHotPost(String sid) {
        String tb_name = "tb_" + sid;
        List<Post> list = postMapper.getSectionHotPost(tb_name);
        return ServerResponse.createBySuccess(list);
    }

    /**
     * @param section
     * @param kind
     * @param order
     * @param page
     * @param limit
     * @Description 获取板块内帖子列表
     * @Param section
     * @Param kind
     * @Param order
     * @Param page
     * @Param limit
     * @Return ServerResponse<List < Notice>>
     */
    @Override
    public ServerResponse<List<PostEntity>> getSectionPostWithPage(String section, String kind, String order, int page, int limit) {
        int start = (page - 1) * limit;
        List list = new ArrayList<>();
        switch (section) {
            case "topic":
                list = topicMapper.getPostWithPage(kind, order, start, limit);
                break;
            case "guide":
                list = guideMapper.getPostWithPage(kind, order, start, limit);
                break;
            case "activity":
                list = activityMapper.getPostWithPage(kind, order, start, limit);
                break;
            case "news":
                list = newsMapper.getPostWithPage(kind, order, start, limit);
                break;
            case "notice":
                list = noticeMapper.getPostWithPage(kind, order, start, limit);
                break;
            case "advice":
                list = adviceMapper.getPostWithPage(kind, order, start, limit);
                break;
        }
        List<PostEntity> rlist = matchUser(list);
        return ServerResponse.createBySuccess(rlist);
    }

    /**
     * @Description 获取板块内帖子列表
     * @Param tid
     * @Param section
     * @Return ServerResponse<HashMap < String, Object>>
     */
    @Override
    public ServerResponse<HashMap<String, Object>> getPostDetail(String tid, String section) {
        Post post = new Post();
        switch (section) {
            case "topic":
                post = topicMapper.selectByPrimaryKey(tid);
                break;
            case "guide":
                post = guideMapper.selectByPrimaryKey(tid);
                break;
            case "activity":
                post = activityMapper.selectByPrimaryKey(tid);
                break;
            case "news":
                post = newsMapper.selectByPrimaryKey(tid);
                break;
            case "notice":
                post = noticeMapper.selectByPrimaryKey(tid);
                break;
            case "advice":
                post = adviceMapper.selectByPrimaryKey(tid);
                break;
        }
        User user = userMapper.getUserOnPostDetail(post.getUid());
        HashMap<String, Object> map = new HashMap<>();
        map.put("post", post);
        map.put("user", user);
        return ServerResponse.createBySuccess(map);
    }

    @Override
    public ServerResponse<List<List<Reply>>> getReplies(String tid) {
        List<Reply> list = replyMapper.getMainReplies(tid);
        List<List<Reply>> replyList = new ArrayList<>();
        for (Reply reply : list) {
            String rrid = reply.getRrid();
            List<Reply> rlist = new ArrayList<>();
            rlist.add(reply);
            Reply rreply = null;
            if (!("".equals(rrid)) && !(null == rrid)) {
                rreply = replyMapper.selectByPrimaryKey(rrid);
                rlist.add(rreply);
            }
            replyList.add(rlist);
        }
        return ServerResponse.createBySuccess(replyList);
    }

}
