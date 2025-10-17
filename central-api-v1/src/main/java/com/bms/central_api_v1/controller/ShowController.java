package com.bms.central_api_v1.controller;

import com.bms.central_api_v1.exception.UnAuthorizedException;
import com.bms.central_api_v1.models.Show;
import com.bms.central_api_v1.requestbody.CreateShowRequestBody;
import com.bms.central_api_v1.requestbody.PurchaseTicketRequestBody;
import com.bms.central_api_v1.responsebody.BillResponseBody;
import com.bms.central_api_v1.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/central/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/create")
    public ResponseEntity createShow(@RequestBody CreateShowRequestBody showRB,
                                     @RequestParam UUID ownerId){
        try{
           Show show =  showService.createShow(showRB, ownerId);
           return new ResponseEntity(show, HttpStatus.CREATED);
        }catch (UnAuthorizedException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/seats")
    public ResponseEntity showAvailableSeats(@RequestParam UUID showId){
        List<Integer> availableSeats = showService.getAvailableSeatsInShow(showId);
        return new ResponseEntity(availableSeats, HttpStatus.OK);
    }

    @PostMapping("/buy")
    public ResponseEntity purchaseTickets(@RequestParam UUID userId,
                                          @RequestParam UUID showId,
                                          @RequestBody PurchaseTicketRequestBody ticketsRB){
        BillResponseBody billResponseBody = showService.purchaseTicket(userId, showId,ticketsRB);
        return new ResponseEntity(billResponseBody, HttpStatus.OK);
    }
}
