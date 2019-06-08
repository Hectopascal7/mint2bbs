package com.mint.service.impl;

import com.alibaba.fastjson.JSON;
import com.mint.common.Const;
import com.mint.common.ServerResponse;
import com.mint.dao.*;
import com.mint.pojo.*;
import com.mint.service.IPostService;
import javafx.geometry.Pos;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.parser.Entity;
import java.text.SimpleDateFormat;
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
    private MessageMapper messageMapper;
    @Autowired
    private CountMapper countMapper;
    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private PraiseMapper praiseMapper;
    @Autowired
    private SectionMapper sectionMapper;
    @Autowired
    private ReplyMapper replyMapper;

    /**
     * @param sid
     * @param title
     * @param content
     * @param httpSession
     * @Description 发帖
     * @Return ServerResponse<User>
     */
    @Override
    public ServerResponse<String> post(String sid, String title, String content, HttpSession httpSession) {
        String tid = UUID.randomUUID().toString();
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        String uid = user.getUid();
        Section section = sectionMapper.selectByPrimaryKey(sid);
        // 阻止非管理员在【社区公告】版块发帖
        if ("社区公告".equals(section.getSname()) && user.getRole() == Const.Role.ROLE_USER) {
            return ServerResponse.createByErrorMessage("抱歉，您暂无权限在【社区公告】板块发帖。");
        }
        String tb_name = section.getTbname();
        Date ptime = new Date(System.currentTimeMillis());
        Integer result = postMapper.post(tb_name, tid, uid, sid, title, ptime, content);
        if (result == 1) {
            // 发帖成功，用户获得5积分。
            Integer updatePointResult = userMapper.updateUserPoint(uid, 5);
            if (Const.OP_SUCCESS == updatePointResult) {
                // 更新帖子数
                Integer updateTcountResult = countMapper.updateUserCount(uid, "tcount", 1);
                if (Const.OP_SUCCESS == updateTcountResult) {
                    // 更新缓存信息
                    user.setPoint(user.getPoint() + 5);
                    httpSession.setAttribute(Const.CURRENT_USER, user);
                    return ServerResponse.createBySuccessMessage("发帖成功！获得5个薄荷币。");
                } else {
                    return ServerResponse.createByErrorMessage("发帖成功！【帖子数增加】异常，请联系管理员。");
                }
            } else {
                return ServerResponse.createByErrorMessage("发帖成功！【积分获取】异常，请联系管理员。");
            }
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
    public ServerResponse<List<HashMap<String, String>>> getAllSticky() {
        List<Post> list = postMapper.getAllSticky();
        List<HashMap<String, String>> t_list = matchUser(list);
        return ServerResponse.createBySuccess(t_list);
    }

    /**
     * @Description 帖子列表Post实体匹配到用户，生成前端对应帖子实体列表List<HashMap<String, String>>
     * @Return ServerResponse<List < Notice>>
     */
    public List<HashMap<String, String>> matchUser(List<Post> list) {
        List<HashMap<String, String>> t_list = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Post post = list.get(i);
            User user = userMapper.getInfoByUid(post.getUid());
            HashMap<String, String> map = new HashMap<>();
            map.put("tid", post.getTid());
            map.put("sid", post.getSid());
            map.put("uid", post.getUid());
            map.put("nickname", user.getNickname());
            map.put("sname", sectionMapper.getSnameBySid(post.getSid()));
            map.put("title", post.getTitle());
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:ss");
            map.put("ptime", sdf.format(post.getPtime()));
            map.put("acount", post.getAcount().toString());
            map.put("rcount", post.getRcount().toString());
            map.put("isbest", post.getIsbest().toString());
            map.put("issticky", post.getIssticky().toString());
            map.put("pcount", post.getPcount().toString());
            map.put("role", user.getRole().toString());
            map.put("point", user.getPoint().toString());
            map.put("profile", user.getProfile());
            t_list.add(map);
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
    public ServerResponse<List<HashMap<String, String>>> getPostByPtime(int page, String kind, String order) {
        List<Post> list = postMapper.getPostByPtime(kind, order, page * 10);
        List<HashMap<String, String>> t_list = matchUser(list);
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
        Section section = sectionMapper.selectByPrimaryKey(sid);
        String tb_name = section.getTbname();
        List<Post> list = postMapper.getSectionHotPost(tb_name);
        return ServerResponse.createBySuccess(list);
    }

    /**
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
    public ServerResponse<List<HashMap<String, String>>> getSectionPostWithPage(String sid, String kind, String order, int page, int limit) {
        int start = (page - 1) * limit;
        Section section = sectionMapper.selectByPrimaryKey(sid);
        String tb_name = section.getTbname();
        List<Post> list = postMapper.getSectionPostWithPage(tb_name, kind, order, start, limit);
        List<HashMap<String, String>> rlist = matchUser(list);
        return ServerResponse.createBySuccess(rlist);
    }

    /**
     * @Description 获取板块内帖子列表
     * @Param tid
     * @Param section
     * @Return ServerResponse<HashMap < String, Object>>
     */
    @Override
    public ServerResponse<HashMap<String, String>> getPostDetail(String tid, String sid, HttpSession httpSession) {
        Section section = sectionMapper.selectByPrimaryKey(sid);
        String tb_name = section.getTbname();
        Post post = postMapper.getSectionPostDetail(tb_name, tid);
        User user = userMapper.getUserOnPostDetail(post.getUid());
        HashMap<String, String> map = new HashMap<>();
        map.put("title", post.getTitle());
        map.put("content", post.getContent());
        map.put("isbest", post.getIsbest().toString());
        map.put("issticky", post.getIssticky().toString());
        map.put("rcount", post.getRcount().toString());

        map.put("uid", user.getUid());
        map.put("nickname", user.getNickname());
        map.put("profile", user.getProfile());
        map.put("signature", user.getSignature());
        map.put("point", user.getPoint().toString());
        map.put("role", user.getRole().toString());
        map.put("sname", section.getSname());
        map.put("tid", post.getTid());
        map.put("sid", post.getSid());
        User u = (User) httpSession.getAttribute(Const.CURRENT_USER);
        String pid = praiseMapper.checkPraise(tid, u.getUid());
        if (StringUtils.isBlank(pid)) {
            map.put("praise", "0");
        } else {
            map.put("praise", "1");
            map.put("pid", pid);
        }

        String cid = collectionMapper.checkCollection(tid, u.getUid());
        if (StringUtils.isBlank(cid)) {
            map.put("collect", "0");
        } else {
            map.put("collect", "1");
            map.put("cid", cid);
        }

        Integer count = messageMapper.checkReport(tid, Const.OPERATION_OBJECT_POST, u.getUid());
        if (count > 0) {
            map.put("report", "1");
        } else {
            map.put("report", "0");
        }

        postMapper.updatePostCount(section.getTbname(), tid, "acount", +1);
        Integer nowCount = (post.getAcount() + 1);
        map.put("acount", nowCount.toString());

        return ServerResponse.createBySuccess(map);
    }


    @Override
    public ServerResponse<List<Post>> getUserLatestTopic(String uid) {
        List<Post> list = postMapper.getUserLatestTopic(uid);
        return ServerResponse.createBySuccess(list);
    }

    @Override
    public ServerResponse<List<Post>> getHomeMoreTopic(String uid) {
        List<Post> list = postMapper.getHomeMoreTopic(uid);
        return ServerResponse.createBySuccess(list);
    }

    @Override
    public ServerResponse<Integer> getSectionPostCount(String sid, String kind) {
        Section section = sectionMapper.selectByPrimaryKey(sid);
        String tb_name = section.getTbname();
        Integer count = postMapper.getSectionPostCount(tb_name, kind);
        return ServerResponse.createBySuccess(count);
    }

    @Override
    public ServerResponse<List<HashMap<String, String>>> getMyPostWithPage(Integer page, Integer limit, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        String uid = user.getUid();
        List<Post> list = postMapper.getMyPostWithPage((page - 1) * limit, limit, uid);
        List<HashMap<String, String>> postList = new ArrayList<>();
        for (Post p : list) {
            HashMap<String, String> map = new HashMap<>();
            map.put("title", p.getTitle());
            map.put("tid", p.getTid());
            map.put("acount", p.getAcount().toString());
            map.put("rcount", p.getRcount().toString());
            Date date = p.getPtime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            map.put("ptime", sdf.format(date));
            map.put("sid", p.getSid());
            postList.add(map);
        }
        return ServerResponse.createBySuccess(postList);
    }

    @Override
    public ServerResponse<Integer> getMyPostCount(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        String uid = user.getUid();
        Integer count = postMapper.getMyPostCount(uid);
        return ServerResponse.createBySuccess(count);
    }

}
