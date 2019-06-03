package com.mint.pojo;

public class Section {
    private String sid;

    private Integer sorder;

    private String sname;

    private String address;

    private String tbname;

    public Section(String sid, Integer sorder, String sname, String address, String tbname) {
        this.sid = sid;
        this.sorder = sorder;
        this.sname = sname;
        this.address = address;
        this.tbname = tbname;
    }

    public Section() {
        super();
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public Integer getSorder() {
        return sorder;
    }

    public void setSorder(Integer sorder) {
        this.sorder = sorder;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTbname() {
        return tbname;
    }

    public void setTbname(String tbname) {
        this.tbname = tbname == null ? null : tbname.trim();
    }
}