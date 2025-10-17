package com.bms.central_api_v1.controller;

import com.bms.central_api_v1.exception.UnAuthorizedException;
import com.bms.central_api_v1.exception.UserNotFoundException;
import com.bms.central_api_v1.models.Theather;
import com.bms.central_api_v1.requestbody.CreateTheatherRB;
import com.bms.central_api_v1.responsebody.GeneralMessageResponse;
import com.bms.central_api_v1.service.AuthService;
import com.bms.central_api_v1.service.TheatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/central/theather")
public class TheatherController {

    @Autowired
    TheatherService theatherService;

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity registerTheather(@RequestBody CreateTheatherRB theatherRB,
                                           @RequestParam UUID ownerId,
                                           @RequestHeader String Authorization){
        try{
            authService.verifyToken(Authorization);
            Theather theather = theatherService.raiseCreateTheatherRequest(theatherRB, ownerId, Authorization);
            return new ResponseEntity(theather, HttpStatus.CREATED);
        }catch (UnAuthorizedException e){
            GeneralMessageResponse message = new GeneralMessageResponse();
            message.setMessage(e.getMessage());
            return new ResponseEntity(message, HttpStatus.UNAUTHORIZED);
        }catch (UserNotFoundException e){
            GeneralMessageResponse message = new GeneralMessageResponse();
            message.setMessage(e.getMessage());
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            GeneralMessageResponse message = new GeneralMessageResponse();
            message.setMessage(e.getMessage());
            return new ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/approve/{theatherId}/{adminId}/{token}")
    public ResponseEntity approveTheatherRequest(@PathVariable UUID theatherId,
                                                 @PathVariable UUID adminId,
                                                 @PathVariable String token){
        try{
            String bearerToken = "Bearer " + token;
            authService.verifyToken(bearerToken);
            // call theather service
            theatherService.acceptTheatherRequest(adminId, theatherId);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            GeneralMessageResponse messageResponse = new GeneralMessageResponse(e.getMessage());
            return  new ResponseEntity(messageResponse, HttpStatus.UNAUTHORIZED);
        }
    }
}
