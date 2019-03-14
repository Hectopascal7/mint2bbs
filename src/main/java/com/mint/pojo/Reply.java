package com.mint.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Reply {
    private String rid;

    private String tid;

    private String rrid;

    private String uid;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date rtime;

    private Integer pcount;

    private String content;

    public Reply(String rid, String tid, String rrid, String uid, Date rtime, Integer pcount) {
        this.rid = rid;
        this.tid = tid;
        this.rrid = rrid;
        this.uid = uid;
        this.rtime = rtime;
        this.pcount = pcount;
    }

    public Reply(String rid, String tid, String rrid, String uid, Date rtime, Integer pcount, String content) {
        this.rid = rid;
        this.tid = tid;
        this.rrid = rrid;
        this.uid = uid;
        this.rtime = rtime;
        this.pcount = pcount;
        this.content = content;
    }

    public Reply() {
        super();
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

    public String getRrid() {
        return rrid;
    }

    public void setRrid(String rrid) {
        this.rrid = rrid == null ? null : rrid.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Date getRtime() {
        return rtime;
    }

    public void setRtime(Date rtime) {
        this.rtime = rtime;
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