package com.mint.pojo;

import java.util.Date;

public class Praise {
    private String pid;

    private String rid;

    private String tid;

    private String uid;

    private Date ptime;

    public Praise(String pid, String rid, String tid, String uid, Date ptime) {
        this.pid = pid;
        this.rid = rid;
        this.tid = tid;
        this.uid = uid;
        this.ptime = ptime;
    }

    public Praise() {
        super();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
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
}