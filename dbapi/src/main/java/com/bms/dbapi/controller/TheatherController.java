package com.bms.dbapi.controller;

import com.bms.dbapi.models.Theather;
import com.bms.dbapi.repository.TheatherRepositorty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/theather")
public class TheatherController {

    @Autowired
    TheatherRepositorty theatherRepositorty;

    @PostMapping("/create")
    public ResponseEntity createTheather(@RequestBody Theather theather){
        theatherRepositorty.save(theather);
        return new ResponseEntity(theather, HttpStatus.CREATED);
    }

    @GetMapping("/{theatherId}")
    public ResponseEntity getTheatherById(@PathVariable UUID theatherId){
        Theather theather = theatherRepositorty.findById(theatherId).orElse(null);
        return new ResponseEntity(theather, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateTheather(@RequestBody Theather theather){
        theatherRepositorty.save(theather);
        return new ResponseEntity(theather, HttpStatus.OK);
    }

}
