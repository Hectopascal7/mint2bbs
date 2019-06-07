package com.mint.dao;

import com.mint.pojo.Count;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CountMapper {
    int deleteByPrimaryKey(String uid);

    int insert(Count record);

    int insertSelective(Count record);

    Count selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(Count record);

    int updateByPrimaryKey(Count record);

    /**
     * @Description 获取活跃用户榜单
     * @Return ServerResponse<String>
     */
    List<Count> getActiveUser();

    Integer updateUserCount(@Param("uid") String uid, @Param("countName") String countName,@Param("getCount")Integer getCount);

}