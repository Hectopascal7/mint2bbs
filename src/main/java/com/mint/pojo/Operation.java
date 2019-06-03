package com.mint.pojo;

import java.util.Date;

public class Operation {
    private String bid;

    private String opUid;

    private String uid;

    private Date btime;

    private Integer opType;

    private Integer opObject;

    private String note;

    public Operation(String bid, String opUid, String uid, Date btime, Integer opType, Integer opObject) {
        this.bid = bid;
        this.opUid = opUid;
        this.uid = uid;
        this.btime = btime;
        this.opType = opType;
        this.opObject = opObject;
    }

    public Operation(String bid, String opUid, String uid, Date btime, Integer opType, Integer opObject, String note) {
        this.bid = bid;
        this.opUid = opUid;
        this.uid = uid;
        this.btime = btime;
        this.opType = opType;
        this.opObject = opObject;
        this.note = note;
    }

    public Operation() {
        super();
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid == null ? null : bid.trim();
    }

    public String getOpUid() {
        return opUid;
    }

    public void setOpUid(String opUid) {
        this.opUid = opUid == null ? null : opUid.trim();
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

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    public Integer getOpObject() {
        return opObject;
    }

    public void setOpObject(Integer opObject) {
        this.opObject = opObject;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}