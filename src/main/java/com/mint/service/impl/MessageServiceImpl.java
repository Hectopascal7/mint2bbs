package com.mint.service.impl;

import com.mint.common.Const;
import com.mint.common.ServerResponse;
import com.mint.dao.GoodMapper;
import com.mint.dao.MessageMapper;
import com.mint.dao.PostMapper;
import com.mint.dao.UserMapper;
import com.mint.pojo.Good;
import com.mint.pojo.Message;
import com.mint.pojo.Post;
import com.mint.pojo.User;
import com.mint.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @Override
    public ServerResponse<List<HashMap<String, String>>> getUnReadMessage(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        String uid = user.getUid();
        List<Message> list = messageMapper.getUnReadMessage(uid);
        List<HashMap<String, String>> mlist = new ArrayList<>();
        for (Message m : list) {
            HashMap<String, String> map = new HashMap<>();
            String suid = m.getSuid();
            User suser = userMapper.selectByPrimaryKey(suid);
            map.put("name", suser.getNickname());
            if (Const.OPERATION_OBJECT_POST == m.getOtype()) {
                map.put("otype", "帖子");
                Post post = postMapper.getReceiveUidByTid(m.getOid());
                map.put("title", post.getTitle());
            } else if (Const.OPERATION_OBJECT_GOOD == m.getOtype()) {
                map.put("otype", "商品");
                System.out.println("oid" + m.getOid());
                Good good = goodMapper.selectByPrimaryKey(m.getOid());
                map.put("title", good.getTitle());
            }
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
            map.put("mtime", sdf.format(m.getMtime()));
            mlist.add(map);
        }
        return ServerResponse.createBySuccess(mlist);
    }
}
