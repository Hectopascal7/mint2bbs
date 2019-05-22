package com.mint.service.impl;

import com.mint.common.ServerResponse;
import com.mint.dao.ResidentMapper;
import com.mint.pojo.Resident;
import com.mint.pojo.User;
import com.mint.service.IResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iResidentService")
public class ResidentServiceImpl implements IResidentService {

    @Autowired
    private ResidentMapper residentMapper;

    @Override
    public ServerResponse<Resident> getUserByLicense(String license) {
        Resident resident=residentMapper.getUserByLicense(license);
        return ServerResponse.createBySuccess(resident);
    }
}
