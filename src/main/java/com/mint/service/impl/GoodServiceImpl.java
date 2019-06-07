package com.mint.service.impl;

import com.mint.common.Const;
import com.mint.common.ServerResponse;
import com.mint.dao.*;
import com.mint.pojo.Good;
import com.mint.pojo.GoodWithBLOBs;
import com.mint.pojo.User;
import com.mint.service.IGoodService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private PraiseMapper praiseMapper;
    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private CountMapper countMapper;
    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public ServerResponse uploadGoodPic(MultipartFile cover, HttpServletRequest httpServletRequest) {
        // 1.保存图片到本地
        String originalFilename = cover.getOriginalFilename();//获取原名字
        String newFilename = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(originalFilename);// 生成新名字
        String uploadURL = httpServletRequest.getSession().getServletContext().getRealPath("/") + "/image/good/" + newFilename;
        try {
            cover.transferTo(new File(uploadURL));
        } catch (IOException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("图片上传成功！");
        }
        return ServerResponse.createBySuccess("图片上传成功！", "/image/good/" + newFilename);
    }

    @Override
    public ServerResponse publishAGood(String title, String isused, String ndegree, String price, String content, String cover, String picture, HttpSession httpSession) {
        String gid = UUID.randomUUID().toString();
        Integer used = Integer.parseInt(isused);
        if (Integer.parseInt(isused) == 0) {
            ndegree = "崭新";
        }
        BigDecimal goodPrice = new BigDecimal(price);
        Date ptime = new Date(System.currentTimeMillis());
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        String uid = user.getUid();
        GoodWithBLOBs good = new GoodWithBLOBs(gid, title, used, ndegree, goodPrice, ptime, 0, uid, 0, content, cover, picture);
        Integer result = goodMapper.insert(good);
        System.out.println("发布结果：" + result);
        if (Const.OP_SUCCESS == result) {
            countMapper.updateUserCount(uid, "gcount", 1);
            userMapper.updateUserPoint(uid, 10);
            return ServerResponse.createBySuccessMessage("发布成功！获得10个薄荷币。");
        } else {
            return ServerResponse.createByErrorMessage("发布商品失败！");
        }
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

    @Override
    public ServerResponse<List<Good>> getMyGoodList(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        List<Good> list = goodMapper.getGoodListByUid(user.getUid());
        return ServerResponse.createBySuccess(list);
    }

    @Override
    public ServerResponse updateGoodPrice(String gid, String price) {
        System.out.println(price);
        BigDecimal newPrice = new BigDecimal(price);
        System.out.println(newPrice);
        Integer result = goodMapper.updateGoodPrice(gid, newPrice);
        if (Const.OP_SUCCESS == result) {
            return ServerResponse.createBySuccessMessage("修改成功！");
        }
        return ServerResponse.createByErrorMessage("修改失败！");
    }

    @Override
    public ServerResponse setGoodIsSaled(String gid) {
        Integer result = goodMapper.setGoodIsSaled(gid);
        if (Const.OP_SUCCESS == result) {
            return ServerResponse.createBySuccessMessage("设置成功！");
        }
        return ServerResponse.createByErrorMessage("设置失败！");
    }

    @Override
    public ServerResponse<HashMap<String, String>> getGoodInfo(String gid, HttpSession httpSession) {
        GoodWithBLOBs good = goodMapper.selectByPrimaryKey(gid);
        if (null != good) {
            HashMap<String, String> map = new HashMap<>();
            map.put("gid", good.getGid());
            map.put("title", good.getTitle());
            map.put("pcount", good.getPcount().toString());
            map.put("isused", good.getIsused().toString());
            map.put("ndegree", good.getNdegree());
            map.put("price", good.getPrice().toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            map.put("ptime", sdf.format(good.getPtime()));
            map.put("content", good.getContent());
            map.put("picture", good.getPicture());
            map.put("issaled", good.getIssaled().toString());
            User u = userMapper.selectByPrimaryKey(good.getUid());
            map.put("uid", u.getUid());
            map.put("role", u.getRole().toString());
            map.put("point", u.getPoint().toString());
            map.put("profile", u.getProfile());
            map.put("nickname", u.getNickname());
            map.put("role", u.getRole().toString());
            map.put("signature", u.getSignature());
            map.put("rcount", replyMapper.getReplyCount(good.getGid()).toString());
            User currUser = (User) httpSession.getAttribute(Const.CURRENT_USER);
            String pid = praiseMapper.checkPraise(gid, currUser.getUid());

            if (StringUtils.isBlank(pid)) {
                map.put("praise", "0");
            } else {
                map.put("praise", "1");
                map.put("pid", pid);
            }
            String cid = collectionMapper.checkCollection(gid, currUser.getUid());
            if (StringUtils.isBlank(cid)) {
                map.put("collect", "0");
            } else {
                map.put("collect", "1");
                map.put("cid", cid);
            }

            Integer count = messageMapper.checkReport(gid, Const.OPERATION_OBJECT_GOOD, currUser.getUid());
            if (count > 0) {
                map.put("report", "1");
            } else {
                map.put("report", "0");
            }

            return ServerResponse.createBySuccess(map);
        } else {
            return ServerResponse.createByErrorMessage("未找到该商品!");
        }
    }

    @Override
    public ServerResponse<List<HashMap<String, String>>> getHotGood() {
        List<Good> list = goodMapper.getHotGood();
        if (list.size() > 0) {
            List<HashMap<String, String>> goodList = new ArrayList<>();
            for (Good good : list) {
                HashMap<String, String> map = new HashMap<>();
                map.put("gid", good.getGid());
                map.put("title", good.getTitle());
                map.put("pcount", good.getPcount().toString());
                goodList.add(map);
            }
            return ServerResponse.createBySuccess(goodList);
        } else {
            return ServerResponse.createBySuccessMessage("商品数量为0。");
        }
    }

}
