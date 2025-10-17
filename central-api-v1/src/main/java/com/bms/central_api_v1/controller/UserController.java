package com.bms.central_api_v1.controller;

import com.bms.central_api_v1.requestbody.CreateUserRB;
import com.bms.central_api_v1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/central/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;
    // Aim: Create endpoint which will get triggered from frontend whenever a particular user wants to register into our application

    @PostMapping("/register")
    public Object registerUser(@RequestBody CreateUserRB userRB){
        // Whatever json payload that json payload will get mapped to CreateUserRB and will create one object of CreateUserRB class.
        // In this method we will recieve request body that is going to contain
        // user details.
        // We need to call our service layer.
        log.info("Request Recieved from client and calling user service");
        return userService.registerUser(userRB);
    }
}
