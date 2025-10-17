package com.bms.central_api_v1.service;

import com.bms.central_api_v1.enums.UserType;
import com.bms.central_api_v1.exception.UserNotFoundException;
import com.bms.central_api_v1.integration.AuthAPI;
import com.bms.central_api_v1.integration.DBAPI;
import com.bms.central_api_v1.models.AppUser;
import com.bms.central_api_v1.requestbody.CreateUserRB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    @Autowired
    DBAPI dbapi;

    @Autowired
    AuthAPI authAPI;



    public Object registerUser(CreateUserRB userRB){
        // Before hitting db api user service will create request for create user endpoint of dbapi
        log.info("Recieved call from controller to service for request body : " + userRB.toString());
        AppUser user = dbapi.callCreateUserEndpoint(userRB);
        return authAPI.callGenerateTokenEndpoint(user.getEmail(), user.getPassword());
    }

    public AppUser getUserById(UUID userId){
        // we need to call db api to get the user record from the database by userid
        return dbapi.callGetUserByIdEndpoint(userId);
    }

    public boolean isTheatherOwner(UUID theatherOwnerId){
        AppUser theatherOwner = this.getUserById(theatherOwnerId);
        if(theatherOwner == null){
            throw new UserNotFoundException(String.format(
                    "Invalid theather OwnerId %s", theatherOwnerId.toString()
            ));
        }
        return theatherOwner.getUserType().equals(UserType.THEATHER_OWNER.toString()) ? true : false;
    }

    public List<AppUser> getAllAdmins(){
        return dbapi.callGetAllAdminsEndpoint();
    }

}
