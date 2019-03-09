package com.mint.service.impl;

import com.mint.common.ServerResponse;
import com.mint.dao.SectionMapper;
import com.mint.pojo.Section;
import com.mint.service.ISectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Program: mint2bbs
 * @Description: 论坛版块Service实现
 * @Author: Jeanne d'Arc
 * @Create: 2019-03-09 09:56:57
 **/
@Service("iSectionService")
public class SectionServiceImpl implements ISectionService {

    @Autowired
    SectionMapper sectionMapper;

    @Override
    public ServerResponse<List<Section>> getSection() {
        return ServerResponse.createBySuccess(sectionMapper.getSection());
    }
}
