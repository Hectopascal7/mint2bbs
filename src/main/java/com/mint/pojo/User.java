package com.mint.pojo;

import java.util.Date;

public class User {
    private String uid;

    private String uname;

    private String sex;

    private String neckname;

    private Date birthday;

    private String carnum;

    private String housenum;

    public User(String uid, String uname, String sex, String neckname, Date birthday, String carnum, String housenum) {
        this.uid = uid;
        this.uname = uname;
        this.sex = sex;
        this.neckname = neckname;
        this.birthday = birthday;
        this.carnum = carnum;
        this.housenum = housenum;
    }

    public User() {
        super();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getNeckname() {
        return neckname;
    }

    public void setNeckname(String neckname) {
        this.neckname = neckname == null ? null : neckname.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum == null ? null : carnum.trim();
    }

    public String getHousenum() {
        return housenum;
    }

    public void setHousenum(String housenum) {
        this.housenum = housenum == null ? null : housenum.trim();
    }
}