package com.mint.service.impl;

import com.mint.common.Const;
import com.mint.common.ServerResponse;
import com.mint.dao.GoodMapper;
import com.mint.dao.UserMapper;
import com.mint.pojo.GoodWithBLOBs;
import com.mint.pojo.User;
import com.mint.service.IGoodService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("iGoodService")
public class GoodServiceImpl implements IGoodService {

    @Autowired
    private GoodMapper goodMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public String uploadGoodCover(MultipartFile cover, HttpServletRequest httpServletRequest) {
        // 1.保存图片到本地
        String originalFilename = cover.getOriginalFilename();//获取原名字
        String newFilename = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(originalFilename);// 生成新名字
        String uploadURL = httpServletRequest.getSession().getServletContext().getRealPath("/") + "/image/good/" + newFilename;
        try {
            cover.transferTo(new File(uploadURL));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/image/good/" + newFilename;
    }

    @Override
    public ServerResponse publishAGood(String title, String isused, String ndegree, String price, String content, String cover, String picture, HttpSession httpSession) {
        String gid = UUID.randomUUID().toString();
        Integer used = Integer.parseInt(isused);
        Integer degree = Integer.parseInt(ndegree);
        BigDecimal goodPrice = new BigDecimal(price);
        Date ptime = new Date(System.currentTimeMillis());
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        String uid = user.getUid();
        GoodWithBLOBs good = new GoodWithBLOBs(gid, title, used, ndegree, goodPrice, ptime, 0, uid, 0, content, cover, picture);
        int result = goodMapper.insert(good);
        System.out.println("发布结果：" + result);
        return ServerResponse.createBySuccessMessage("发布成功！");
    }

    @Override
    public ServerResponse<List<HashMap<String, String>>> getGoodListWithPage(String isused, String order, int page) {
        Integer used = Integer.parseInt(isused);
        List<GoodWithBLOBs> list = goodMapper.getGoodListWithPage(used, order, page * 8);
        List<HashMap<String, String>> goodList = new ArrayList<>();
        for (GoodWithBLOBs good : list) {
            HashMap<String, String> map = new HashMap<>();
            User user = userMapper.selectByPrimaryKey(good.getUid());
            map.put("nickname", user.getNickname());
            map.put("profile", user.getProfile());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            map.put("ptime", sdf.format(good.getPtime()));
            map.put("issaled", good.getIssaled().toString());
            map.put("title", good.getTitle());
            map.put("price", good.getPrice().toString());
            map.put("gid", good.getGid());
            map.put("cover", good.getCover());
            map.put("content", good.getContent());
            map.put("ndegree", good.getNdegree());
            goodList.add(map);
        }
        return ServerResponse.createBySuccess(goodList);
    }

}
