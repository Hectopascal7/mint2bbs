package com.mint.pojo;

import java.util.Date;

public class User {

    // 用户识别ID，UUID生成
    private String uid;

    // 用户登录ID
    private String loginid;

    // 用户密码
    private String password;

    // 用户昵称
    private String nickname;

    // 用户角色 0-管理员，1-普通用户，2-版主
    private String character;

    // 用户真实姓名
    private String name;

    // 用户性别
    private Integer sex;

    // 用户身份证号
    private String idcnum;

    // 用户手机号码
    private String phone;

    // 用户生日
    private Date birthday;

    // 用户住所
    private String house;

    // 用户车牌号
    private String license;

    //用户头像
    private String profile;

    // 用户积分
    private Integer point;

    // 用户注册时间
    private Date jointime;

    public User(String uid, String loginid, String password, String nickname, String character, String name, Integer sex, String idcnum, String phone, Date birthday, String house, String license, String profile, Integer point, Date jointime) {
        this.uid = uid;
        this.loginid = loginid;
        this.password = password;
        this.nickname = nickname;
        this.character = character;
        this.name = name;
        this.sex = sex;
        this.idcnum = idcnum;
        this.phone = phone;
        this.birthday = birthday;
        this.house = house;
        this.license = license;
        this.profile = profile;
        this.point = point;
        this.jointime = jointime;
    }

    public User() {
        super();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid == null ? null : loginid.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character == null ? null : character.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdcnum() {
        return idcnum;
    }

    public void setIdcnum(String idcnum) {
        this.idcnum = idcnum == null ? null : idcnum.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house == null ? null : house.trim();
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license == null ? null : license.trim();
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile == null ? null : profile.trim();
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Date getJointime() {
        return jointime;
    }

    public void setJointime(Date jointime) {
        this.jointime = jointime;
    }
}