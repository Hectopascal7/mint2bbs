package com.mint.pojo;

import java.util.Date;

public class Collection {
    private String cid;

    private String tid;

    private String uid;

    private Date ctime;

    public Collection(String cid, String tid, String uid, Date ctime) {
        this.cid = cid;
        this.tid = tid;
        this.uid = uid;
        this.ctime = ctime;
    }

    public Collection() {
        super();
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
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

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}