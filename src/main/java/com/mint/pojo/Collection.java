package com.mint.pojo;

import java.util.Date;

public class Collection {
    private String cid;

    private String iid;

    private Integer itype;

    private String isid;

    private String cuid;

    private Date ctime;

    public Collection(String cid, String iid, Integer itype, String isid, String cuid, Date ctime) {
        this.cid = cid;
        this.iid = iid;
        this.itype = itype;
        this.isid = isid;
        this.cuid = cuid;
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

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid == null ? null : cuid.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}