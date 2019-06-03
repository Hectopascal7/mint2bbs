package com.mint.pojo;

import java.util.Date;

public class Message {
    private String mid;

    private String suid;

    private String ruid;

    private Integer mtype;

    private String oid;

    private Date mtime;

    private Integer otype;

    private Integer isread;

    public Message(String mid, String suid, String ruid, Integer mtype, String oid, Date mtime, Integer otype, Integer isread) {
        this.mid = mid;
        this.suid = suid;
        this.ruid = ruid;
        this.mtype = mtype;
        this.oid = oid;
        this.mtime = mtime;
        this.otype = otype;
        this.isread = isread;
    }

    public Message() {
        super();
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid == null ? null : mid.trim();
    }

    public String getSuid() {
        return suid;
    }

    public void setSuid(String suid) {
        this.suid = suid == null ? null : suid.trim();
    }

    public String getRuid() {
        return ruid;
    }

    public void setRuid(String ruid) {
        this.ruid = ruid == null ? null : ruid.trim();
    }

    public Integer getMtype() {
        return mtype;
    }

    public void setMtype(Integer mtype) {
        this.mtype = mtype;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public Integer getOtype() {
        return otype;
    }

    public void setOtype(Integer otype) {
        this.otype = otype;
    }

    public Integer getIsread() {
        return isread;
    }

    public void setIsread(Integer isread) {
        this.isread = isread;
    }
}