package com.mint.pojo;

import java.util.Date;

public class Advice extends Post{
    private String tid;

    private String uid;

    private String sid;

    private String title;

    private Date ptime;

    private Integer acount;

    private Integer rcount;

    private Integer isbest;

    private Integer issticky;

    private Integer pcount;

    private String content;

    public Advice(String tid, String uid, String sid, String title, Date ptime, Integer acount, Integer rcount, Integer isbest, Integer issticky, Integer pcount) {
        this.tid = tid;
        this.uid = uid;
        this.sid = sid;
        this.title = title;
        this.ptime = ptime;
        this.acount = acount;
        this.rcount = rcount;
        this.isbest = isbest;
        this.issticky = issticky;
        this.pcount = pcount;
    }

    public Advice(String tid, String uid, String sid, String title, Date ptime, Integer acount, Integer rcount, Integer isbest, Integer issticky, Integer pcount, String content) {
        this.tid = tid;
        this.uid = uid;
        this.sid = sid;
        this.title = title;
        this.ptime = ptime;
        this.acount = acount;
        this.rcount = rcount;
        this.isbest = isbest;
        this.issticky = issticky;
        this.pcount = pcount;
        this.content = content;
    }

    public Advice() {
        super();
    }

    public Advice(String tid, String uid, String sid, String title, Date ptime, String content) {
        super(tid, uid, sid, title, ptime, content);
        this.tid = tid;
        this.uid = uid;
        this.sid = sid;
        this.title = title;
        this.ptime = ptime;
        this.content = content;
    }


    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}