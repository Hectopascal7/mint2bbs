package com.mint.service;

import com.mint.common.ServerResponse;
import com.mint.pojo.Section;

import java.util.List;

/**
 * @Program: mint2bbs
 * @Description: 论坛板块Service接口
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-09 09:55:08
 **/
public interface ISectionService {

    public ServerResponse<List<Section>> getAllSection();

}
