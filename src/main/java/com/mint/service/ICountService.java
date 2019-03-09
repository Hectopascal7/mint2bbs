package com.mint.service;

import com.mint.common.ServerResponse;

import java.util.HashMap;
import java.util.List;

/**
 * @Program: mint2bbs
 * @Description: 数量Service接口
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-09 21:42:53
 **/
public interface ICountService {
    ServerResponse<List<HashMap<String, String>>> getActiveUser();
}
