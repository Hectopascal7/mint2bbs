package com.mint.pojo;

public class Count {
    private String uid;

    private Integer tcount;

    private Integer rcount;

    private Integer pcount;

    private Integer gcount;

    private Integer ccount;

    public Count(String uid, Integer tcount, Integer rcount, Integer pcount, Integer gcount, Integer ccount) {
        this.uid = uid;
        this.tcount = tcount;
        this.rcount = rcount;
        this.pcount = pcount;
        this.gcount = gcount;
        this.ccount = ccount;
    }

    public Count() {
        super();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Integer getTcount() {
        return tcount;
    }

    public void setTcount(Integer tcount) {
        this.tcount = tcount;
    }

    public Integer getRcount() {
        return rcount;
    }

    public void setRcount(Integer rcount) {
        this.rcount = rcount;
    }

    public Integer getPcount() {
        return pcount;
    }

    public void setPcount(Integer pcount) {
        this.pcount = pcount;
    }

    public Integer getGcount() {
        return gcount;
    }

    public void setGcount(Integer gcount) {
        this.gcount = gcount;
    }

    public Integer getCcount() {
        return ccount;
    }

    public void setCcount(Integer ccount) {
        this.ccount = ccount;
    }
}