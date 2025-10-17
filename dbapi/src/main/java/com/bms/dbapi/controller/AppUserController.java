package com.bms.dbapi.controller;

import com.bms.dbapi.models.AppUser;
import com.bms.dbapi.repository.AppUserRepository;
import com.bms.dbapi.responsebody.AdminsResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/user")
@Slf4j
public class AppUserController {

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/email/{emailId}")
    public ResponseEntity getUserByEmail(@PathVariable String emailId){
        AppUser user = appUserRepository.findByEmail(emailId);
        return new ResponseEntity(user, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody AppUser user){
        appUserRepository.save(user);
        log.info("Recieved request with the request body : " + user.toString());
        return new ResponseEntity(user, HttpStatus.CREATED);
    }

    // http//localhost:8081/api/v1/db/user/nw1i338hfn3dd3db3d
    @GetMapping("/{userId}")
    public ResponseEntity getUserById(@PathVariable UUID userId){
        AppUser user = appUserRepository.findById(userId).orElse(null);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateUserById(@RequestBody AppUser user){
        appUserRepository.save(user);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUserById(@PathVariable UUID userId){
        appUserRepository.deleteById(userId);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/admins")
    public ResponseEntity getAllAdmins(){
        List<AppUser> admins = appUserRepository.getAllAdmins();
        AdminsResponseBody response = new AdminsResponseBody();
        response.setAdmins(admins);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
