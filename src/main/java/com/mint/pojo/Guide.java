package com.mint.pojo;

import java.util.Date;

public class Guide {
    private String nid;

    private String uid;

    private String title;

    private Date ptime;

    private Integer acount;

    private Integer rcount;

    private Integer isbest;

    private Integer issticky;

    private String content;

    public Guide(String nid, String uid, String title, Date ptime, Integer acount, Integer rcount, Integer isbest, Integer issticky) {
        this.nid = nid;
        this.uid = uid;
        this.title = title;
        this.ptime = ptime;
        this.acount = acount;
        this.rcount = rcount;
        this.isbest = isbest;
        this.issticky = issticky;
    }

    public Guide(String nid, String uid, String title, Date ptime, Integer acount, Integer rcount, Integer isbest, Integer issticky, String content) {
        this.nid = nid;
        this.uid = uid;
        this.title = title;
        this.ptime = ptime;
        this.acount = acount;
        this.rcount = rcount;
        this.isbest = isbest;
        this.issticky = issticky;
        this.content = content;
    }

    public Guide() {
        super();
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid == null ? null : nid.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}