package com.mint.pojo;

import java.util.Date;

public class Checkin {
    private String cid;

    private String uid;

    private Date ctime;

    private Date lastctime;

    private Integer days;

    public Checkin(String cid, String uid, Date ctime, Date lastctime, Integer days) {
        this.cid = cid;
        this.uid = uid;
        this.ctime = ctime;
        this.lastctime = lastctime;
        this.days = days;
    }

    public Checkin() {
        super();
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
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

    public Date getLastctime() {
        return lastctime;
    }

    public void setLastctime(Date lastctime) {
        this.lastctime = lastctime;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}