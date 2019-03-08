package com.mint.pojo;

import java.math.BigDecimal;

public class Good {
    private String gid;

    private String title;

    private Integer isnew;

    private Integer degree;

    private BigDecimal price;

    public Good(String gid, String title, Integer isnew, Integer degree, BigDecimal price) {
        this.gid = gid;
        this.title = title;
        this.isnew = isnew;
        this.degree = degree;
        this.price = price;
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

    public Integer getIsnew() {
        return isnew;
    }

    public void setIsnew(Integer isnew) {
        this.isnew = isnew;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}