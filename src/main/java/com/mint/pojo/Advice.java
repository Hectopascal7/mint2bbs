package com.mint.pojo;

import java.util.Date;

public class Advice {
    private String nid;

    private String uid;

    private String title;

    private Date publishtime;

    private Integer accesscount;

    private Integer replycount;

    private Integer isbest;

    private Integer issticky;

    private String content;

    public Advice(String nid, String uid, String title, Date publishtime, Integer accesscount, Integer replycount, Integer isbest, Integer issticky) {
        this.nid = nid;
        this.uid = uid;
        this.title = title;
        this.publishtime = publishtime;
        this.accesscount = accesscount;
        this.replycount = replycount;
        this.isbest = isbest;
        this.issticky = issticky;
    }

    public Advice(String nid, String uid, String title, Date publishtime, Integer accesscount, Integer replycount, Integer isbest, Integer issticky, String content) {
        this.nid = nid;
        this.uid = uid;
        this.title = title;
        this.publishtime = publishtime;
        this.accesscount = accesscount;
        this.replycount = replycount;
        this.isbest = isbest;
        this.issticky = issticky;
        this.content = content;
    }

    public Advice() {
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

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public Integer getAccesscount() {
        return accesscount;
    }

    public void setAccesscount(Integer accesscount) {
        this.accesscount = accesscount;
    }

    public Integer getReplycount() {
        return replycount;
    }

    public void setReplycount(Integer replycount) {
        this.replycount = replycount;
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