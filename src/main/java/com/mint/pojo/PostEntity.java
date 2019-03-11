package com.mint.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class PostEntity {
    private String tid;

    private String nickname;

    private String sname;

    private String title;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date ptime;

    private Integer acount;

    private Integer rcount;

    private Integer isbest;

    private Integer issticky;

    private Integer pcount;

    private Integer role;

    private Integer ulevel;

    private String profile;

    public PostEntity(String tid, String nickname, String sname, String title, Date ptime, Integer acount, Integer rcount, Integer isbest, Integer issticky, Integer pcount, Integer role, Integer ulevel) {
        this.tid = tid;
        this.nickname = nickname;
        this.sname = sname;
        this.title = title;
        this.ptime = ptime;
        this.acount = acount;
        this.rcount = rcount;
        this.isbest = isbest;
        this.issticky = issticky;
        this.pcount = pcount;
        this.role = role;
        this.ulevel = ulevel;
    }

    public PostEntity(String tid, String nickname, String sname, String title, Date ptime, Integer acount, Integer rcount, Integer isbest, Integer issticky, Integer pcount, Integer role, Integer ulevel, String profile) {
        this.tid = tid;
        this.nickname = nickname;
        this.sname = sname;
        this.title = title;
        this.ptime = ptime;
        this.acount = acount;
        this.rcount = rcount;
        this.isbest = isbest;
        this.issticky = issticky;
        this.pcount = pcount;
        this.role = role;
        this.ulevel = ulevel;
        this.profile = profile;
    }

    public PostEntity() {
        super();
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getPtime() {
        return ptime;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }

    public Integer getAcount() {
        return acount;
    }

    public void setAcount(Integer acount) {
        this.acount = acount;
    }

    public Integer getRcount() {
        return rcount;
    }

    public void setRcount(Integer rcount) {
        this.rcount = rcount;
    }

    public Integer getIsbest() {
        return isbest;
    }

    public void setIsbest(Integer isbest) {
        this.isbest = isbest;
    }

    public Integer getIssticky() {
        return issticky;
    }

    public void setIssticky(Integer issticky) {
        this.issticky = issticky;
    }

    public Integer getPcount() {
        return pcount;
    }

    public void setPcount(Integer pcount) {
        this.pcount = pcount;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getUlevel() {
        return ulevel;
    }

    public void setUlevel(Integer ulevel) {
        this.ulevel = ulevel;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile == null ? null : profile.trim();
    }
}