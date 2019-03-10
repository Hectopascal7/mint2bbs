package com.mint.pojo;

import java.util.Date;
import java.util.Objects;

public class User {
    private String uid;

    private String loginid;

    private String password;

    private String nickname;

    private Integer role;

    private Integer sex;

    private Date birthday;

    private String license;

    private Integer point;

    private Date jointime;

    private Integer ulevel;

    private String email;

    private String signature;

    private Integer status;

    private String profile;

    public User(String uid, String loginid, String password, String nickname, Integer role, Integer sex, Date birthday, String license, Integer point, Date jointime, Integer ulevel, String email, String signature, Integer status) {
        this.uid = uid;
        this.loginid = loginid;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.sex = sex;
        this.birthday = birthday;
        this.license = license;
        this.point = point;
        this.jointime = jointime;
        this.ulevel = ulevel;
        this.email = email;
        this.signature = signature;
        this.status = status;
    }

    public User(String uid, String loginid, String password, String nickname, Integer role, Integer sex, Date birthday, String license, Integer point, Date jointime, Integer ulevel, String email, String signature, Integer status, String profile) {
        this.uid = uid;
        this.loginid = loginid;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.sex = sex;
        this.birthday = birthday;
        this.license = license;
        this.point = point;
        this.jointime = jointime;
        this.ulevel = ulevel;
        this.email = email;
        this.signature = signature;
        this.status = status;
        this.profile = profile;
    }

    public User(String nickname, Integer role, String profile, Integer ulevel) {
        this.nickname = nickname;
        this.role = role;
        this.ulevel = ulevel;
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", loginid='" + loginid + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", role=" + role +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", license='" + license + '\'' +
                ", point=" + point +
                ", jointime=" + jointime +
                ", ulevel=" + ulevel +
                ", email='" + email + '\'' +
                ", signature='" + signature + '\'' +
                ", status=" + status +
                ", profile='" + profile + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(uid, user.uid) &&
                Objects.equals(loginid, user.loginid) &&
                Objects.equals(password, user.password) &&
                Objects.equals(nickname, user.nickname) &&
                Objects.equals(role, user.role) &&
                Objects.equals(sex, user.sex) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(license, user.license) &&
                Objects.equals(point, user.point) &&
                Objects.equals(jointime, user.jointime) &&
                Objects.equals(ulevel, user.ulevel) &&
                Objects.equals(email, user.email) &&
                Objects.equals(signature, user.signature) &&
                Objects.equals(status, user.status) &&
                Objects.equals(profile, user.profile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, loginid, password, nickname, role, sex, birthday, license, point, jointime, ulevel, email, signature, status, profile);
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile == null ? null : profile.trim();
    }
}