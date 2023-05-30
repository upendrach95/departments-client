package com.service.departmentsclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.departmentsclient.model.RequestModel;
import com.service.departmentsclient.model.ResponseModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ClientServiceImpl implements ClientService {

    private final ObjectMapper objectMapper;

    private  final RestTemplate restTemplate;

    @Value("${custom.url}")
    private String URL;



    private ClientServiceImpl(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
        this.restTemplate = new RestTemplate();
    }
    @Override
    public ResponseModel getData(int id) {
        String localUrl = URL + "/id/" + id;
        try{
            String data = restTemplate.getForObject(localUrl, String.class);
            return objectMapper.readValue(data,ResponseModel.class);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }


    }

    @Override
    public ResponseModel postData(RequestModel requestModel) throws JsonProcessingException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<RequestModel> requestEntity = new HttpEntity<>(requestModel, httpHeaders);

        ResponseEntity<String> responseEntity;

        try {
            responseEntity = restTemplate.postForEntity(URL, requestEntity, String.class);
            System.out.println(responseEntity);
            if(responseEntity.getStatusCode().is2xxSuccessful()){
                System.out.println(responseEntity.getStatusCode());
                System.out.println(responseEntity.getBody());
                return objectMapper.readValue(responseEntity.getBody(), ResponseModel.class);
            }
        }
        catch (Exception ex){
            return null;

        }

        return null;
    }
}
