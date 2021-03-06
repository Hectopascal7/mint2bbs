package com.mint.service.impl;

import com.alibaba.fastjson.JSON;
import com.mint.common.Const;
import com.mint.common.ServerResponse;
import com.mint.dao.*;
import com.mint.pojo.*;
import com.mint.service.IMessageService;
import javafx.geometry.Pos;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("iMessageService")
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private GoodMapper goodMapper;
    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private SectionMapper sectionMapper;

    @Override
    public ServerResponse<List<HashMap<String, String>>> getUserMessage(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        String uid = user.getUid();
        List<Message> list = messageMapper.getUserMessage(uid);
        List<HashMap<String, String>> mlist = new ArrayList<>();
        for (Message m : list) {
            HashMap<String, String> map = new HashMap<>();
            String suid = m.getSuid();
            User suser = userMapper.selectByPrimaryKey(suid);
            map.put("name", suser.getNickname());
            map.put("suid", suid);
            if (Const.OPERATION_TYPE_REPLY == m.getMtype()) {
                map.put("mtype", "回复");
            } else if (Const.OPERATION_TYPE_REPORT == m.getMtype()) {
                map.put("mtype", "举报");
            }

            if (Const.OPERATION_OBJECT_POST == m.getOtype()) {
                map.put("otype", "帖子");
                Post post = postMapper.getReceiveUidByTid(m.getOid());
                map.put("title", post.getTitle());
                map.put("part", "post");
                map.put("sid", post.getSid());
                map.put("tid", post.getTid());
                if (Const.OPERATION_TYPE_REPORT == m.getMtype()) {
                    map.put("uid", post.getUid());
                    map.put("nickname", userMapper.getNicknameByUid(post.getUid()));
                }
            } else if (Const.OPERATION_OBJECT_GOOD == m.getOtype()) {
                map.put("otype", "商品");
                map.put("part", "good");
                System.out.println("oid" + m.getOid());
                map.put("gid", m.getOid());
                Good good = goodMapper.selectByPrimaryKey(m.getOid());
                map.put("title", good.getTitle());
                if (Const.OPERATION_TYPE_REPORT == m.getMtype()) {
                    map.put("uid", good.getUid());
                    map.put("nickname", userMapper.getNicknameByUid(good.getUid()));
                }
            } else if (Const.OPERATION_OBJECT_REPLY == m.getOtype()) {
                map.put("otype", "回复");
                Reply reply = replyMapper.selectByPrimaryKey(m.getOid());
                map.put("content", reply.getContent());
                if (StringUtils.isBlank(reply.getSid())) {
                    Good good = goodMapper.selectByPrimaryKey(reply.getTid());
                    map.put("gid", good.getGid());
                    map.put("part", "good");
                } else {
                    String tb_name = sectionMapper.selectByPrimaryKey(reply.getSid()).getTbname();
                    Post post = postMapper.getPostByTid(reply.getTid(), tb_name);
                    map.put("part", "post");
                    map.put("sid", post.getSid());
                    map.put("tid", post.getTid());
                }

                if (!StringUtils.isBlank(reply.getRrid())) {
                    Reply myReply = replyMapper.selectByPrimaryKey(reply.getRrid());
                    map.put("mycontent", myReply.getContent());
                }

                if (Const.OPERATION_TYPE_REPORT == m.getMtype()) {
                    map.put("uid", reply.getUid());
                    map.put("nickname", userMapper.getNicknameByUid(reply.getUid()));
                }
            }
            map.put("mid", m.getMid());
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
            map.put("mtime", sdf.format(m.getMtime()));
            map.put("isread", m.getIsread().toString());
            mlist.add(map);
        }
        System.out.println(JSON.toJSONString(ServerResponse.createBySuccess(mlist)));
        return ServerResponse.createBySuccess(mlist);
    }

    // 举报
    @Override
    public ServerResponse report(Integer mtype, String oid, Integer otype, HttpSession httpSession) {
        String mid = UUID.randomUUID().toString();
        Date mtime = new Date(System.currentTimeMillis());
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        String suid = user.getUid();
//        String ruid="";
        Message message = new Message(mid, suid, "ec42e476-ec50-44b1-9d2d-ba98618113c6", mtype, oid, mtime, otype, 0);
        Integer result = messageMapper.insert(message);
        if (Const.OP_SUCCESS == result) {
            return ServerResponse.createBySuccessMessage("已经反馈给系统管理员，感谢您的监督！");
        } else {
            return ServerResponse.createByErrorMessage("反馈失败，请联系管理员！");
        }
    }

    @Override
    public ServerResponse updateMessageRead(String mid, HttpSession httpSession) {
        if (StringUtils.isBlank(mid)) {
            User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
            messageMapper.clearAllMessage(user.getUid());
            return ServerResponse.createBySuccessMessage("全部设为已读成功！");
        } else {
            Integer result = messageMapper.updateMessageRead(mid);
            if (Const.OP_SUCCESS == result) {
                return ServerResponse.createBySuccessMessage("查看成功！");
            } else {
                return ServerResponse.createByErrorMessage("更新消息状态失败！");
            }
        }
    }

    @Override
    public ServerResponse deleteMessage(String mid, HttpSession httpSession) {
        if (StringUtils.isBlank(mid)) {
            User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
            messageMapper.deleteUserReadMessage(user.getUid());
            return ServerResponse.createBySuccessMessage("清空全部已读信息成功！");
        } else {
            Integer result = messageMapper.deleteByPrimaryKey(mid);
            if (Const.OP_SUCCESS == result) {
                return ServerResponse.createBySuccessMessage("删除成功！");
            } else {
                return ServerResponse.createByErrorMessage("删除消息失败！");
            }
        }
    }
}
