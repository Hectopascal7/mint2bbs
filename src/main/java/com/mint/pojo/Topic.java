package com.mint.pojo;

import java.util.Date;

public class Topic {
    private Integer tid;

    private String title;

    private String content;

    private String publisherid;

    private Date publishtime;

    private String accesscount;

    private String replycount;

    private Integer partid;

    public Topic(Integer tid, String title, String content, String publisherid, Date publishtime, String accesscount, String replycount, Integer partid) {
        this.tid = tid;
        this.title = title;
        this.content = content;
        this.publisherid = publisherid;
        this.publishtime = publishtime;
        this.accesscount = accesscount;
        this.replycount = replycount;
        this.partid = partid;
    }

    public Topic() {
        super();
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getPublisherid() {
        return publisherid;
    }

    public void setPublisherid(String publisherid) {
        this.publisherid = publisherid == null ? null : publisherid.trim();
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public String getAccesscount() {
        return accesscount;
    }

    public void setAccesscount(String accesscount) {
        this.accesscount = accesscount == null ? null : accesscount.trim();
    }

    public String getReplycount() {
        return replycount;
    }

    public void setReplycount(String replycount) {
        this.replycount = replycount == null ? null : replycount.trim();
    }

    public Integer getPartid() {
        return partid;
    }

    public void setPartid(Integer partid) {
        this.partid = partid;
    }
}