package com.mint.dao;

import com.mint.pojo.Weibo;

import java.util.List;

public interface WeiboMapper {
    int deleteByPrimaryKey(String tid);

    int insert(Weibo record);

    int insertSelective(Weibo record);

    Weibo selectByPrimaryKey(String tid);

    int updateByPrimaryKeySelective(Weibo record);

    int updateByPrimaryKeyWithBLOBs(Weibo record);

    int updateByPrimaryKey(Weibo record);

    List<Weibo> getWeiboWithPage(Integer limit);

    Integer getTotalWeiboCount();
}