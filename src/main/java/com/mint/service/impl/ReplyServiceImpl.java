package com.mint.service.impl;

import com.mint.common.Const;
import com.mint.common.ServerResponse;
import com.mint.dao.*;
import com.mint.pojo.*;
import com.mint.service.IReplyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("iReplyService")
public class ReplyServiceImpl implements IReplyService {

    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private SectionMapper sectionMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodMapper goodMapper;
    @Autowired
    private PraiseMapper praiseMapper;
    @Autowired
    private CountMapper countMapper;

    @Override
    public ServerResponse<List<HashMap<String, String>>> getUserLatestReply(String uid) {
        List<Reply> list = replyMapper.getUserLatestReply(uid);
        List<HashMap<String, String>> latestReply = new ArrayList<HashMap<String, String>>();
        for (Reply reply : list) {
            HashMap<String, String> map = new HashMap<String, String>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
            String ptime = simpleDateFormat.format(reply.getRtime());
            map.put("ptime", ptime);
            if (StringUtils.isBlank(reply.getSid())) {
                Good good = goodMapper.selectByPrimaryKey(reply.getTid());
                map.put("type", "good");
                map.put("gid", good.getGid());
                map.put("title", good.getTitle());
            } else {
                String sid = reply.getSid();
                String tb_name = sectionMapper.selectByPrimaryKey(sid).getTbname();
                Post post = postMapper.getReplyPost(tb_name, reply.getTid());
                map.put("type", "post");
                map.put("sid", post.getSid());
                map.put("title", post.getTitle());
                map.put("tid", post.getTid());
            }
            map.put("content", reply.getContent());
            latestReply.add(map);
        }
        return ServerResponse.createBySuccess(latestReply);
    }

    @Override
    public ServerResponse<List<HashMap<String, String>>> getReplies(String tid, String sid, HttpSession httpSession) {
        List<Reply> list = replyMapper.getMainReplies(tid);
        List<HashMap<String, String>> replyList = new ArrayList<>();
        for (Reply reply : list) {
            HashMap<String, String> map = new HashMap<>();
            User user = userMapper.selectByPrimaryKey(reply.getUid());
            map.put("profile", user.getProfile());
            map.put("nickname", user.getNickname());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            map.put("rtime", sdf.format(reply.getRtime()));
            map.put("rid", reply.getRid());
            map.put("content", reply.getContent());
            map.put("pcount", reply.getPcount().toString());
            map.put("role", user.getRole().toString());
            map.put("point", user.getPoint().toString());
            User u = (User) httpSession.getAttribute(Const.CURRENT_USER);
            String pid = praiseMapper.checkPraise(reply.getRid(), u.getUid());
            if (StringUtils.isBlank(pid)) {
                map.put("praise", "0");
            } else {
                map.put("praise", "1");
                map.put("pid", pid);
            }

            if (!StringUtils.isBlank(reply.getRrid())) {
                Reply r_reply = replyMapper.selectByPrimaryKey(reply.getRrid());
                User r_user = userMapper.selectByPrimaryKey(r_reply.getUid());
                map.put("rreply", "1");
                map.put("rcontent", r_reply.getContent());
                map.put("rnickname", r_user.getNickname());
            }

            if (!StringUtils.isBlank(sid)) {
                String tb_name = sectionMapper.selectByPrimaryKey(sid).getTbname();
                Post post = postMapper.getPostByTid(tid, tb_name);
                if (reply.getUid().equals(post.getUid())) {
                    map.put("isauthor", "1");
                } else {
                    map.put("isauthor", "0");
                }
            } else {
                Good good = goodMapper.selectByPrimaryKey(tid);
                if (reply.getUid().equals(good.getUid())) {
                    map.put("isauthor", "1");
                } else {
                    map.put("isauthor", "0");
                }
            }
            replyList.add(map);
        }
        return ServerResponse.createBySuccess(replyList);
    }

    @Override
    public ServerResponse<List<HashMap<String, String>>> getHomeMoreReply(String uid) {
        List<Reply> list = replyMapper.getHomeMoreReply(uid);
        List<HashMap<String, String>> latestReply = new ArrayList<HashMap<String, String>>();
        for (Reply reply : list) {
            HashMap<String, String> map = new HashMap<String, String>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
            String ptime = simpleDateFormat.format(reply.getRtime());
            map.put("ptime", ptime);
            if (StringUtils.isBlank(reply.getSid())) {
                Good good = goodMapper.selectByPrimaryKey(reply.getTid());
                System.out.println(reply.getRid());
                map.put("type", "good");
                map.put("gid", good.getGid());
                map.put("title", good.getTitle());
            } else {
                String sid = reply.getSid();
                String tb_name = sectionMapper.selectByPrimaryKey(sid).getTbname();
                Post post = postMapper.getReplyPost(tb_name, reply.getTid());
                map.put("type", "post");
                map.put("sid", post.getSid());
                map.put("title", post.getTitle());
                map.put("tid", post.getTid());
            }
            map.put("content", reply.getContent());
            latestReply.add(map);
        }
        return ServerResponse.createBySuccess(latestReply);
    }

    @Override
    public ServerResponse reply(String tid, String content, String rrid, String sid, HttpSession httpSession) {
        System.out.println(rrid);
        if (StringUtils.isBlank(rrid)) {
            rrid = null;
        }
        System.out.println(rrid);
        String rid = UUID.randomUUID().toString();
        Date rtime = new Date(System.currentTimeMillis());
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        Reply reply = new Reply(rid, tid, rrid, user.getUid(), rtime, 0, sid, content);
        System.out.println(tid);
        Integer result = replyMapper.insert(reply);
        if (Const.OP_SUCCESS == result) {
            String uid = "";
            Integer otype = 0;
            if (!StringUtils.isBlank(sid)) {
                Post post = postMapper.getReceiveUidByTid(tid);
                Section section = sectionMapper.selectByPrimaryKey(post.getSid());
                postMapper.updatePostCount(section.getTbname(), post.getTid(), "rcount", 1);
                if (StringUtils.isBlank(rrid)) {
                    uid = post.getUid();
                    otype = Const.OPERATION_OBJECT_POST;
                } else {
                    Reply r_reply = replyMapper.selectByPrimaryKey(rrid);
                    uid = r_reply.getUid();
                    otype = Const.OPERATION_OBJECT_REPLY;
                }
            } else {
                System.out.println(goodMapper.selectByPrimaryKey(tid));
                GoodWithBLOBs good = goodMapper.selectByPrimaryKey(tid);
                if (StringUtils.isBlank(rrid)) {
                    uid = good.getUid();
                    otype = Const.OPERATION_OBJECT_GOOD;
                } else {
                    Reply r_reply = replyMapper.selectByPrimaryKey(rrid);
                    uid = r_reply.getUid();
                    otype = Const.OPERATION_OBJECT_REPLY;
                }
            }
            String mid = UUID.randomUUID().toString();
            System.out.println(user);
            if (Const.OPERATION_OBJECT_REPLY == otype) {
                Message message = new Message(mid, user.getUid(), uid, Const.OPERATION_TYPE_REPLY, rid, rtime, otype, 0);
                messageMapper.insert(message);
            } else {
                Message message = new Message(mid, user.getUid(), uid, Const.OPERATION_TYPE_REPLY, tid, rtime, otype, 0);
                messageMapper.insert(message);
            }
            // 回复成功，回复用户薄荷币+1
            userMapper.updateUserPoint(user.getUid(), 1);
            // 回复成功，回复用户回复数+1
            countMapper.updateUserCount(user.getUid(), "rcount", 1);
            user.setPoint(user.getPoint() + 1);
            httpSession.setAttribute(Const.CURRENT_USER, user);
            return ServerResponse.createBySuccessMessage("回复成功！获得1个薄荷币。");
        } else {
            return ServerResponse.createByErrorMessage("回复失败！");
        }
    }
}
