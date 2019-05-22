package com.mint.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import java.util.Date;

public class Good {
    private String gid;

    private String title;

    private Integer isused;

    private String ndegree;

    private BigDecimal price;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date ptime;

    private Integer pcount;

    private String uid;

    private Integer issaled;

    public Good(String gid, String title, Integer isused, String ndegree, BigDecimal price, Date ptime, Integer pcount, String uid, Integer issaled) {
        this.gid = gid;
        this.title = title;
        this.isused = isused;
        this.ndegree = ndegree;
        this.price = price;
        this.ptime = ptime;
        this.pcount = pcount;
        this.uid = uid;
        this.issaled = issaled;
    }

    public Good() {
        super();
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid == null ? null : gid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getIsused() {
        return isused;
    }

    public void setIsused(Integer isused) {
        this.isused = isused;
    }

    public String getNdegree() {
        return ndegree;
    }

    public void setNdegree(String ndegree) {
        this.ndegree = ndegree == null ? null : ndegree.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getPtime() {
        return ptime;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }

    public Integer getPcount() {
        return pcount;
    }

    public void setPcount(Integer pcount) {
        this.pcount = pcount;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Integer getIssaled() {
        return issaled;
    }

    public void setIssaled(Integer issaled) {
        this.issaled = issaled;
    }
}