package com.mint.pojo;

import java.util.Date;

public class Praise {
    private String pid;

    private String iid;

    private Integer itype;

    private String isid;

    private String puid;

    private Date ptime;

    public Praise(String pid, String iid, Integer itype, String isid, String puid, Date ptime) {
        this.pid = pid;
        this.iid = iid;
        this.itype = itype;
        this.isid = isid;
        this.puid = puid;
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

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid == null ? null : iid.trim();
    }

    public Integer getItype() {
        return itype;
    }

    public void setItype(Integer itype) {
        this.itype = itype;
    }

    public String getIsid() {
        return isid;
    }

    public void setIsid(String isid) {
        this.isid = isid == null ? null : isid.trim();
    }

    public String getPuid() {
        return puid;
    }

    public void setPuid(String puid) {
        this.puid = puid == null ? null : puid.trim();
    }

    public Date getPtime() {
        return ptime;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }
}