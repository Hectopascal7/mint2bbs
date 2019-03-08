package com.mint.pojo;

import java.util.Objects;

public class Resident {
    private String uid;

    private String name;

    private Integer building;

    private Integer unit;

    private String room;

    private String phone;

    private String idcnum;

    public Resident(String uid, String name, Integer building, Integer unit, String room, String phone, String idcnum) {
        this.uid = uid;
        this.name = name;
        this.building = building;
        this.unit = unit;
        this.room = room;
        this.phone = phone;
        this.idcnum = idcnum;
    }

    @Override
    public String toString() {
        return "Resident{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", building=" + building +
                ", unit=" + unit +
                ", room='" + room + '\'' +
                ", phone='" + phone + '\'' +
                ", idcnum='" + idcnum + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resident resident = (Resident) o;
        return Objects.equals(uid, resident.uid) &&
                Objects.equals(name, resident.name) &&
                Objects.equals(building, resident.building) &&
                Objects.equals(unit, resident.unit) &&
                Objects.equals(room, resident.room) &&
                Objects.equals(phone, resident.phone) &&
                Objects.equals(idcnum, resident.idcnum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, name, building, unit, room, phone, idcnum);
    }

    public Resident() {
        super();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getBuilding() {
        return building;
    }

    public void setBuilding(Integer building) {
        this.building = building;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room == null ? null : room.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getIdcnum() {
        return idcnum;
    }

    public void setIdcnum(String idcnum) {
        this.idcnum = idcnum == null ? null : idcnum.trim();
    }
}