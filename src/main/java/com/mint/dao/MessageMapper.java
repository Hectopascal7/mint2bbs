package com.mint.dao;

import com.mint.pojo.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(String mid);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(String mid);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    List<Message> getUserMessage(@Param("ruid") String ruid);

    Integer updateMessageRead(String mid);

    Integer clearAllMessage(String ruid);

    Integer deleteUserReadMessage(String ruid);

    Integer checkReport(@Param("oid") String oid, @Param("otype") Integer otype, @Param("suid") String suid);
}