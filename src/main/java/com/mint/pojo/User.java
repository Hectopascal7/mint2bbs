package com.mint.pojo;

import java.util.Date;

public class User {
    private String loginid;

    private String uid;

    private String password;

    private String nickname;

    private Integer role;

    private Integer sex;

    private Date birthday;

    private String license;

    private String profile;

    private Integer point;

    private Date jointime;

    private Integer level;

    private String email;

    private String signature;

    public User(String loginid, String uid, String password, String nickname, Integer role, Integer sex, Date birthday, String license, String profile, Integer point, Date jointime, Integer level, String email, String signature) {
        this.loginid = loginid;
        this.uid = uid;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.sex = sex;
        this.birthday = birthday;
        this.license = license;
        this.profile = profile;
        this.point = point;
        this.jointime = jointime;
        this.level = level;
        this.email = email;
        this.signature = signature;
    }

    public User() {
        super();
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid == null ? null : loginid.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }
}