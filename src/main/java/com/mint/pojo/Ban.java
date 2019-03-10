package com.mint.pojo;

import java.util.Date;

public class Ban {
    private String bid;

    private String uid;

    private Date btime;

    private String note;

    public Ban(String bid, String uid, Date btime) {
        this.bid = bid;
        this.uid = uid;
        this.btime = btime;
    }

    public Ban(String bid, String uid, Date btime, String note) {
        this.bid = bid;
        this.uid = uid;
        this.btime = btime;
        this.note = note;
    }

    public Ban() {
        super();
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid == null ? null : bid.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Date getBtime() {
        return btime;
    }

    public void setBtime(Date btime) {
        this.btime = btime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}