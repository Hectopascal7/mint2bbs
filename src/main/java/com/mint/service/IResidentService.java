package com.mint.service;

import com.mint.common.ServerResponse;
import com.mint.pojo.Resident;

public interface IResidentService {

    ServerResponse<Resident> getUserByLicense(String license);
}
