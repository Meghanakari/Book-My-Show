
package com.bms.central_api_v1.controller;


import com.bms.central_api_v1.exception.UnAuthorizedException;
import com.bms.central_api_v1.models.Hall;
import com.bms.central_api_v1.requestbody.CreateHallRB;
import com.bms.central_api_v1.responsebody.GeneralMessageResponse;
import com.bms.central_api_v1.service.AuthService;
import com.bms.central_api_v1.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/central/hall")
public class HallController {

    @Autowired
    AuthService authService;

    @Autowired
    HallService hallService;

    @PostMapping("/create")
    public ResponseEntity createHall(@RequestParam UUID ownerId,
                                     @RequestParam UUID theatherId,
                                     @RequestHeader String Authorization,
                                     @RequestBody CreateHallRB hallRB){

        try{
            authService.verifyToken(Authorization);
            Hall hall  =hallService.createHall(ownerId, theatherId, hallRB);
            return new ResponseEntity(hall, HttpStatus.CREATED);
        }catch (UnAuthorizedException e){
            GeneralMessageResponse gm = new GeneralMessageResponse();
            gm.setMessage(e.getMessage());
            return new ResponseEntity(gm, HttpStatus.UNAUTHORIZED);
        }

    }
}
