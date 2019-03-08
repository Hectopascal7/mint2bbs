package com.mint.pojo;

import java.util.Date;

public class Tutsau {
    private String tid;

    private String uid;

    private Date ptime;

    private String content;

    public Tutsau(String tid, String uid, Date ptime) {
        this.tid = tid;
        this.uid = uid;
        this.ptime = ptime;
    }

    public Tutsau(String tid, String uid, Date ptime, String content) {
        this.tid = tid;
        this.uid = uid;
        this.ptime = ptime;
        this.content = content;
    }

    public Tutsau() {
        super();
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

    public Date getPtime() {
        return ptime;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}