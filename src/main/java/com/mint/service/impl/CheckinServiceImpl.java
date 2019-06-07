package com.mint.service.impl;

import com.mint.common.Const;
import com.mint.common.ServerResponse;
import com.mint.dao.CheckinMapper;
import com.mint.dao.UserMapper;
import com.mint.pojo.Checkin;
import com.mint.pojo.User;
import com.mint.service.ICheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service("iCheckinService")
public class CheckinServiceImpl implements ICheckinService {

    @Autowired
    private CheckinMapper checkinMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse checkin(HttpSession httpSession) {
        Date ctime = new Date(System.currentTimeMillis());
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        String uid = user.getUid();
        String cid = UUID.randomUUID().toString();
        Checkin checkin = checkinMapper.getUserLatestCheckinInfo(uid);
        Checkin nowCheckin;
        Integer latestDays = 1;
        if (null == checkin) {
            nowCheckin = new Checkin(cid, uid, ctime, null, 1);
        } else {
            Date latest = checkin.getCtime();
            if (checkIfYesterday(latest)) {
                nowCheckin = new Checkin(cid, uid, ctime, checkin.getCtime(), checkin.getDays() + 1);
                latestDays = checkin.getDays() + 1;
            } else {
                nowCheckin = new Checkin(cid, uid, ctime, checkin.getCtime(), 1);
            }
        }
        Integer result = checkinMapper.insert(nowCheckin);
        Integer getPoint;
        if (Const.OP_SUCCESS == result) {
            if (latestDays < 5) {
                getPoint = 5;
            } else if (latestDays >= 5 && latestDays < 15) {
                getPoint = 10;
            } else if (latestDays >= 15 && latestDays < 30) {
                getPoint = 15;
            } else {
                getPoint = 30;
            }
            Integer updatePointResult = userMapper.updateUserPoint(uid, getPoint);
            if (Const.OP_SUCCESS == updatePointResult) {
                // 更新用户缓存信息
                user.setPoint(user.getPoint() + getPoint);
                httpSession.setAttribute(Const.CURRENT_USER, user);
                return ServerResponse.createBySuccessMessage("连续签到" + latestDays + "天，获得" + getPoint + "薄荷币。");
            } else {
                return ServerResponse.createByErrorMessage("签到成功，【获得积分】异常，请联系管理员。");
            }
        } else {
            return ServerResponse.createByErrorMessage("签到失败！");
        }
    }

    @Override
    public ServerResponse<Checkin> getUserCheckinInfo(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        Checkin checkin = checkinMapper.getUserLatestCheckinInfo(user.getUid());
        return ServerResponse.createBySuccess(checkin);
    }

    public static boolean checkIfYesterday(Date date) {
        //获得昨天的值
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date yesterday = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(yesterday);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String yesterdayStr = sdf.format(calendar.getTime());
        String dateStr = sdf.format(date);
        //比较
        return yesterdayStr.equals(dateStr);
    }

}
