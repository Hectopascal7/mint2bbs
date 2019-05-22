package com.mint.service;

import com.mint.common.ServerResponse;
import com.mint.pojo.Resident;
import com.mint.pojo.User;

public interface IResidentService {

    ServerResponse<Resident> getUserByLicense(String license);
}
