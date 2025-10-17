package com.bms.authentication_api_v1.integration;

import com.bms.authentication_api_v1.model.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class DBAPI extends RestAPI  {

    @Value("${db.api.base}")
    String dbApiBase;

    @Autowired
    ModelMapper modelMapper;

    public AppUser callGetUserByEmailEndpoint(String email){
        String endPoint = "/user/email/" + email;
        Object response = this.makeGetCall(dbApiBase, endPoint, new HashMap<>());
        if(response == null){
            return null;
        }
        AppUser userResp = modelMapper.map(response, AppUser.class);
        return userResp;
    }
}
