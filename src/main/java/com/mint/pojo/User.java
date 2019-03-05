package com.mint.pojo;

import java.util.Date;

public class User {
    private String uid;

    private String loginid;

    private String password;

    private String nickname;

    private Integer role;

    private String name;

    private Integer sex;

    private String idcnum;

    private String phone;

    private Date birthday;

    private String house;

    private String license;

    private String profile;

    private Integer point;

    private Date jointime;

    private Integer ulevel;

    private String email;

    public User(String uid, String loginid, String password, String nickname, Integer role, String name, Integer sex, String idcnum, String phone, Date birthday, String house, String license, String profile, Integer point, Date jointime, Integer ulevel, String email) {
        this.uid = uid;
        this.loginid = loginid;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
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
        this.ulevel = ulevel;
        this.email = email;
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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
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

    public Integer getUlevel() {
        return ulevel;
    }

    public void setUlevel(Integer ulevel) {
        this.ulevel = ulevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}