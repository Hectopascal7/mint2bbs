package com.mint.dao;

import com.mint.pojo.Reply;

public interface ReplyMapper {
    int deleteByPrimaryKey(String rid);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(String rid);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKeyWithBLOBs(Reply record);

    int updateByPrimaryKey(Reply record);
}