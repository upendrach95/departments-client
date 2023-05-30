package com.service.departmentsclient.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.departmentsclient.model.RequestModel;
import com.service.departmentsclient.model.ResponseModel;
import com.service.departmentsclient.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("departments-client")
public class Controller {

    @Autowired
    ClientService clientservice;

    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseModel> getData(@PathVariable int id) {
        ResponseModel responseModel = clientservice.getData(id);
        if (responseModel == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})

    public ResponseEntity<ResponseModel> createDepartment(@RequestBody RequestModel requestModel) throws JsonProcessingException, JsonProcessingException {
        ResponseModel responseModel = clientservice.postData(requestModel);

        if(responseModel == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

}
