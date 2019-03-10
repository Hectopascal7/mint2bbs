package com.mint.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Good {
    private String gid;

    private String title;

    private Integer isused;

    private Integer ndegree;

    private BigDecimal price;

    private Date ptime;

    private Integer pcount;

    public Good(String gid, String title, Integer isused, Integer ndegree, BigDecimal price, Date ptime, Integer pcount) {
        this.gid = gid;
        this.title = title;
        this.isused = isused;
        this.ndegree = ndegree;
        this.price = price;
        this.ptime = ptime;
        this.pcount = pcount;
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

    public Integer getNdegree() {
        return ndegree;
    }

    public void setNdegree(Integer ndegree) {
        this.ndegree = ndegree;
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
}