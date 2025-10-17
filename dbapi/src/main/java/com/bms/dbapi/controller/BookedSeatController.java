package com.bms.dbapi.controller;

import com.bms.dbapi.models.BookedSeat;
import com.bms.dbapi.repository.BookedSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/bookedseat")
public class BookedSeatController {

    @Autowired
    BookedSeatRepository bookedSeatRepository;

    @GetMapping("/check")
    public ResponseEntity isSeatBooked(@RequestParam UUID showId,
                                       @RequestParam int seat){
        BookedSeat bookedSeat = bookedSeatRepository.getBookedSeat(showId, seat);
        return new ResponseEntity(bookedSeat, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity bookSeat(@RequestBody BookedSeat bookedSeat){
        bookedSeatRepository.save(bookedSeat);
        return new ResponseEntity(bookedSeat, HttpStatus.CREATED);
    }

}
