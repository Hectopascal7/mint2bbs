package com.mint.service.impl;

import com.mint.common.Const;
import com.mint.common.ServerResponse;
import com.mint.dao.CountMapper;
import com.mint.dao.OperationMapper;
import com.mint.dao.ResidentMapper;
import com.mint.dao.UserMapper;
import com.mint.pojo.Count;
import com.mint.pojo.Resident;
import com.mint.pojo.User;
import com.mint.service.IUserService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Program: mint2bbs
 * @Description: IUserService的实现类
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-05 15:47:56
 **/
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ResidentMapper residentMapper;
    @Autowired
    private CountMapper countMapper;
    @Autowired
    private OperationMapper operationMapper;

    /**
     * @Description 用户登录
     * @Param loginid
     * @Param password
     * @Return ServerResponse<User>
     */
    @Override
    public ServerResponse<User> login(String loginid, String password) {
        // 检查登录id是否存在
        int resultCount = userMapper.checkLoginid(loginid);
        // 登录id不存在
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("账号不存在,请检查后重新输入...");
        }
        // 登录id存在，校验账号密码是否匹配
        User user = userMapper.checkUser(loginid, password);
        // 登录id和密码匹配不到用户，登录失败
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码输入错误!");
        }
        // 登录id和密码匹配，保障账户安全，将用户实体密码置为空
        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功，正在跳转...", user);
    }

    /**
     * @Description 用户注册
     * @Param user
     * @Param resident
     * @Return ServerResponse<String>
     */
    @Override
    public ServerResponse<String> register(String loginid, String nickname, String password, String uid, String name, String sex, String birthday, String building, String unit, String floor, String room, String idcnum, String phone, String role) {
        Integer r = Integer.parseInt(role);
        Integer s = Integer.parseInt(sex);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date b = null;
        try {
            b = simpleDateFormat.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date jointime = new Date(System.currentTimeMillis());
        Resident resident = new Resident(uid, name, building, unit, floor, room, phone, idcnum);
        // 检查住户信息是否正确
        if (checkResident(resident)) {
            // 住户信息正确，检查该住户是否被管理员删除过
            Integer banCount = operationMapper.checkUserIfDeleted(resident.getUid());
            if (banCount != 0) {
                // 住户被删除，无资格注册
                return ServerResponse.createByErrorMessage("该用户已被移除，无法注册！");
            } else {
                // 住户未被删除，有注册资格，检查注册该住户是否已注册
                if (checkUidIfRegistered(uid)) {
                    // 已注册，无法再次注册
                    return ServerResponse.createByErrorMessage("该用户已注册，注册失败！");
                } else {
                    // 未注册，检查登录id是否存在
                    int resultCount = userMapper.checkLoginid(loginid);
                    // 登录id不存在，可以进行注册
                    if (resultCount == 0) {
                        String profile = "";
                        // 设置默认头像
                        if (1 == s) {
                            // 性别：男
                            profile = "/image/asuna.jpg";
                        } else {
                            // 性别：女
                            profile = "/image/kantai.jpg";
                        }
                        // 生成用户信息对象
//                        User user = new User(uid, loginid, password, nickname, r, s, b, 0, jointime, 1, profile);
                        User user = new User(uid, loginid, password, nickname, r, s, b, "未设置", 0, jointime, "未设置", "Ta还没填写个性签名呢~", Const.USER_STATUS_NORMAL, profile);
                        // 将用户信息存入用户信息表
                        System.out.println(user);
                        int result = userMapper.insertSelective(user);
                        // 存储成功，注册成功
                        if (result == 1) {
                            // 初始化用户贴子数、赞赏数等信息
                            Count count = new Count(user.getUid(), 0, 0, 0, 0, 0);
                            countMapper.insert(count);
                            // 所有操作完毕，执行成功
                            return ServerResponse.createBySuccessMessage("注册成功！");
                        } else {
                            // 存储失败，注册失败
                            return ServerResponse.createByErrorMessage("注册失败！");
                        }
                    } else {
                        // 登录id已存在，无法进行注册
                        return ServerResponse.createByErrorMessage("注册账号已存在，请重新输入。");
                    }
                }
            }
        } else {
            // 住户信息校验失败，无法注册
            return ServerResponse.createByErrorMessage("用户信息校验错误！");
        }
    }

    @Override
    public ServerResponse updatePassword(String now_password, String new_password, String re_new_password, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        String uid = user.getUid();
        if (!checkNowPassword(now_password, uid)) {
            return ServerResponse.createByErrorMessage("当前密码错误，修改密码失败！");
        } else {
            if (!new_password.equals(re_new_password)) {
                return ServerResponse.createByErrorMessage("新密码与确认密码不同，修改失败！");
            } else {
                Integer result = userMapper.updatePassword(new_password, uid);
                if (1 == result) {
                    return ServerResponse.createBySuccessMessage("更新密码成功，即将跳转登录页，请重新登录。");
                } else {
                    return ServerResponse.createByErrorMessage("密码更新失败！请联系管理员解决。");
                }
            }
        }
    }

    @Override
    public ServerResponse updateUserInfo(String nickname, String email, String license, String signature, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        String uid = user.getUid();
        if (checkNickname(nickname, uid)) {
            return ServerResponse.createByErrorMessage("更新用户信息失败，用户昵称已被使用！");
        } else {
            Integer result = userMapper.updateUserInfo(uid, nickname, email, license, signature);
            if (Const.OP_SUCCESS == result) {
                User u = userMapper.selectByPrimaryKey(uid);
                u.setPassword(StringUtils.EMPTY);
                httpSession.setAttribute(Const.CURRENT_USER, u);
                return ServerResponse.createBySuccessMessage("更新个人信息成功！");
            } else {
                return ServerResponse.createByErrorMessage("更新个人信息失败！");
            }
        }
    }

    @Override
    public ServerResponse<String> uploadProfile(MultipartFile profile, HttpServletRequest httpServletRequest) {
        // 1.保存图片到本地
        String originalFilename = profile.getOriginalFilename();//获取原名字
        String newFilename = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(originalFilename);// 生成新名字
        String uploadURL = httpServletRequest.getSession().getServletContext().getRealPath("/") + "/image/user/" + newFilename;
        try {
            profile.transferTo(new File(uploadURL));
        } catch (IOException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("图片上传失败！");
        }
        return ServerResponse.createBySuccess("图片上传成功！", "/image/user/" + newFilename);
    }

    @Override
    public ServerResponse updateUserProfile(String profile, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute(Const.CURRENT_USER);
        String uid = user.getUid();
        Integer result = userMapper.updateUserProfile(profile, uid);
        if (Const.OP_SUCCESS == result) {
            User u = userMapper.selectByPrimaryKey(uid);
            u.setPassword(StringUtils.EMPTY);
            httpSession.setAttribute(Const.CURRENT_USER, u);
            return ServerResponse.createBySuccessMessage("更新用户头像成功！");
        } else {
            return ServerResponse.createByErrorMessage("更新用户头像失败！");
        }
    }

    @Override
    public ServerResponse<String> getUidByNickname(String nickname) {
        String uid = userMapper.getUidByNickname(nickname);
        return ServerResponse.createBySuccess(uid);
    }


    @Override
    public User getIndexUserInfo(String uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

    /**
     * @Description 检查住户信息是否正确
     * @Param resident
     * @Return boolean
     */
    public boolean checkResident(Resident resident) {
        // 根据用户识别码查找用户
        Resident result = residentMapper.selectByPrimaryKey(resident.getUid());
        System.out.println("1" + resident);
        System.out.println("2" + result);
        // 用户存在
        if (null != result && resident.equals(result)) {
            return true;
            // 用户不存在
        } else {
            return false;
        }
    }

    /**
     * @Description 检查住户信息是否正确
     * @Param resident
     * @Return boolean
     */
    public boolean checkUidIfRegistered(String uid) {
        // 根据用户识别码查找用户
        User result = userMapper.selectByPrimaryKey(uid);
        // 用户已注册
        if (null != result) {
            return true;
            // 用户未注册
        } else {
            return false;
        }
    }

    public boolean checkNowPassword(String now_password, String uid) {
        Integer count = userMapper.checkNowPassword(now_password, uid);
        if (1 == count) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkNickname(String nickname, String uid) {
        Integer count = userMapper.checkNickname(nickname, uid);
        if (1 == count) {
            return true;
        } else {
            return false;
        }
    }

}
