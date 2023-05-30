package com.service.departmentsclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.departmentsclient.model.RequestModel;
import com.service.departmentsclient.model.ResponseModel;

public interface ClientService {
    ResponseModel getData(int id);

    ResponseModel postData(RequestModel requestModel) throws JsonProcessingException;
}
