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

    @Override
    public ServerResponse<List<HashMap<String, String>>> getUserLatestReply(String uid) {
        List<Reply> list = replyMapper.getUserLatestReply(uid);
        List<HashMap<String, String>> latestReply = new ArrayList<HashMap<String, String>>();
        for (Reply reply : list) {
            HashMap<String, String> map = new HashMap<String, String>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
            String ptime = simpleDateFormat.format(reply.getRtime());
            map.put("ptime", ptime);
            String sid = reply.getSid();
            System.out.println(sid);
            String tb_name = sectionMapper.selectByPrimaryKey(sid).getTbname();
            Post post = postMapper.getReplyPost(tb_name, reply.getTid());
            map.put("sid", post.getSid());
            map.put("title", post.getTitle());
            map.put("content", reply.getContent());
            map.put("tid", post.getTid());
            latestReply.add(map);
        }
        return ServerResponse.createBySuccess(latestReply);
    }

    @Override
    public ServerResponse<List<HashMap<String, String>>> getReplies(String tid, String sid) {
        List<Reply> list = replyMapper.getMainReplies(tid);
        List<HashMap<String, String>> replyList = new ArrayList<>();
        for (Reply reply : list) {
            HashMap<String, String> map = new HashMap<>();
//            String rrid = reply.getRrid();
//            List<Reply> rlist = new ArrayList<>();
//            rlist.add(reply);
//            if (!("".equals(rrid)) && !(null == rrid)) {
//                Reply rreply = replyMapper.selectByPrimaryKey(rrid);
//                rlist.add(rreply);
//            }
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
            String sid = reply.getSid();
            String tb_name = sectionMapper.selectByPrimaryKey(sid).getTbname();
            Post post = postMapper.getReplyPost(tb_name, reply.getTid());
            map.put("sid", post.getSid());
            map.put("title", post.getTitle());
            map.put("content", reply.getContent());
            map.put("tid", post.getTid());
            latestReply.add(map);
        }
        return ServerResponse.createBySuccess(latestReply);
    }

    @Override
    public ServerResponse reply(String tid, String content, String sid, HttpSession httpSession) {
        String rid = UUID.randomUUID().toString();
        Date rtime = new Date(System.currentTimeMillis());
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        Reply reply = new Reply(rid, tid, null, user.getUid(), rtime, 0, sid, content);
        System.out.println(tid);
        Integer result = replyMapper.insert(reply);
        if (Const.OP_SUCCESS == result) {
            String uid = "";
            Integer otype = 0;
            if (!StringUtils.isBlank(sid)) {
                Post post = postMapper.getReceiveUidByTid(tid);
                uid = post.getUid();
                otype = 30;
            } else {
                System.out.println(goodMapper.selectByPrimaryKey(tid));
                GoodWithBLOBs good = goodMapper.selectByPrimaryKey(tid);
                uid = good.getUid();
                otype = 50;
            }
            String mid = UUID.randomUUID().toString();
            System.out.println(user);
            Message message = new Message(mid, user.getUid(), uid, Const.OPERATION_TYPE_REPLY, tid, rtime, otype, 0);
            messageMapper.insert(message);
            return ServerResponse.createBySuccessMessage("回复成功！");
        } else {
            return ServerResponse.createByErrorMessage("回复失败！");
        }
    }
}
