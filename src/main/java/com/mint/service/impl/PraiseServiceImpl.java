package com.mint.service.impl;

import com.mint.common.Const;
import com.mint.common.ServerResponse;
import com.mint.dao.*;
import com.mint.pojo.*;
import com.mint.service.IPraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * @Program: mint2bbs
 * @Description: 点赞相关Service实现
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-15 12:38:37
 **/
@Service("iPraiseService")
public class PraiseServiceImpl implements IPraiseService {

    @Autowired
    private PraiseMapper praiseMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private CountMapper countMapper;
    @Autowired
    private GoodMapper goodMapper;
    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private SectionMapper sectionMapper;

    @Override
    public ServerResponse<String> praise(String iid, int itype, String isid, HttpSession session) {
        String pid = UUID.randomUUID().toString();
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        Date ptime = new Date(System.currentTimeMillis());
        System.out.println(itype);
        Praise praise = new Praise(pid, iid, itype, isid, user.getUid(), ptime);
        int result = praiseMapper.insert(praise);
        if (result == 1) {
            String p_uid = "";
            if (Const.OPERATION_OBJECT_GOOD == itype) {
                Good good = goodMapper.selectByPrimaryKey(iid);
                goodMapper.updateGoodPcount(iid, 1);
                p_uid = good.getUid();
            } else if (Const.OPERATION_OBJECT_REPLY == itype) {
                Reply reply = replyMapper.selectByPrimaryKey(iid);
                replyMapper.updateReplyPcount(iid, 1);
                p_uid = reply.getUid();
            } else {
                Section section = sectionMapper.selectByPrimaryKey(isid);
                Post post = postMapper.getPostByTid(iid, section.getTbname());
                p_uid = post.getUid();
                postMapper.updatePostCount(section.getTbname(), iid, "pcount", 1);
            }
            countMapper.updateUserCount(p_uid, "pcount", 1);
            return ServerResponse.createBySuccess("点赞成功！", pid);
        } else {
            return ServerResponse.createByError();
        }
    }

    @Override
    public ServerResponse<HashMap<String, String>> cancelPraise(String pid) {
        Praise praise = praiseMapper.selectByPrimaryKey(pid);
        int result = praiseMapper.deleteByPrimaryKey(pid);
        if (result == 1) {
            HashMap<String, String> map = new HashMap<>();
            if (praise.getItype() == Const.OPERATION_OBJECT_POST) {
                Post post = postMapper.getReceiveUidByTid(praise.getIid());
                Section section = sectionMapper.selectByPrimaryKey(post.getSid());
                postMapper.updatePostCount(section.getTbname(), post.getTid(), "pcount", -1);
                countMapper.updateUserCount(post.getUid(), "pcount", -1);
                map.put("isid", post.getSid());
                map.put("itype", Const.OPERATION_OBJECT_POST.toString());
            } else if (praise.getItype() == Const.OPERATION_OBJECT_GOOD) {
                goodMapper.updateGoodPcount(praise.getIid(), -1);
                Good good = goodMapper.selectByPrimaryKey(praise.getIid());
                countMapper.updateUserCount(good.getUid(), "pcount", -1);
                map.put("itype", Const.OPERATION_OBJECT_GOOD.toString());
            } else {
                Reply reply = replyMapper.selectByPrimaryKey(praise.getIid());
                replyMapper.updateReplyPcount(praise.getIid(), -1);
                countMapper.updateUserCount(reply.getUid(), "pcount", -1);
                map.put("itype", Const.OPERATION_OBJECT_REPLY.toString());
            }
            map.put("iid", praise.getIid());
            return ServerResponse.createBySuccess("取消点赞成功！", map);
        } else {
            return ServerResponse.createByErrorMessage("取消点赞失败！");
        }
    }
}
