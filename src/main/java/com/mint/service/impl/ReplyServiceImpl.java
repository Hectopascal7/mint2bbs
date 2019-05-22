package com.mint.service.impl;

import com.mint.common.ServerResponse;
import com.mint.dao.PostMapper;
import com.mint.dao.ReplyMapper;
import com.mint.pojo.Post;
import com.mint.pojo.Reply;
import com.mint.service.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service("iReplyService")
public class ReplyServiceImpl implements IReplyService {

    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private PostMapper postMapper;

    @Override
    public ServerResponse<List<HashMap<String, String>>> getUserLatestReply(String uid) {
        List<Reply> list = replyMapper.getUserLatestReply(uid);
        List<HashMap<String, String>> latestReply = new ArrayList<HashMap<String, String>>();
        for (Reply reply : list) {
            HashMap<String, String> map = new HashMap<String, String>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
            String ptime = simpleDateFormat.format(reply.getRtime());
            map.put("ptime", ptime);
            String section = reply.getSid();
            String tb_name = "tb_" + section;
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
    public ServerResponse<List<HashMap<String, String>>> getHomeMoreReply(String uid) {
        List<Reply> list = replyMapper.getHomeMoreReply(uid);
        List<HashMap<String, String>> latestReply = new ArrayList<HashMap<String, String>>();
        for (Reply reply : list) {
            HashMap<String, String> map = new HashMap<String, String>();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
            String ptime = simpleDateFormat.format(reply.getRtime());
            map.put("ptime", ptime);
            String section = reply.getSid();
            String tb_name = "tb_" + section;
            Post post = postMapper.getReplyPost(tb_name, reply.getTid());
            map.put("sid", post.getSid());
            map.put("title", post.getTitle());
            map.put("content", reply.getContent());
            map.put("tid", post.getTid());
            latestReply.add(map);
        }
        return ServerResponse.createBySuccess(latestReply);
    }
}
