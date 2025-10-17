package com.bms.notification_v1_api.controller;

import com.bms.notification_v1_api.requestbody.TheatherRequestRB;
import com.bms.notification_v1_api.service.TheatherMailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/notify/theather")
public class TheatherController {

    @Autowired
    TheatherMailService mailService;

    @PutMapping("/request")
    public void notifyAdminForCreateTheatherRequest(@RequestBody TheatherRequestRB theatherRequestRB) throws MessagingException {

        mailService.notifyAdminForCreateTheatherRequest(theatherRequestRB);

    }

}
