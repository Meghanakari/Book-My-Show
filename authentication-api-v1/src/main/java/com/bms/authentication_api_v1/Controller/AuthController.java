package com.bms.authentication_api_v1.Controller;

import com.bms.authentication_api_v1.Service.AuthService;
import com.bms.authentication_api_v1.responsebody.SuccessResponseBody;
import com.bms.authentication_api_v1.responsebody.TokenResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthService authService;




    @GetMapping("/token")
    public ResponseEntity getToken(@RequestParam String userId,
                                   @RequestParam String password){
        String jwtToken  = authService.generateToken(userId, password);
        TokenResponseBody response = new TokenResponseBody();
        response.setToken(jwtToken);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/verify-token")
    public ResponseEntity verifyToken(){

        SuccessResponseBody successRB = new SuccessResponseBody();
        successRB.setStatus("Success");
        return new ResponseEntity(successRB, HttpStatus.OK);

    }
}
