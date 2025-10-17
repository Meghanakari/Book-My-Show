
package com.bms.central_api_v1.service;

import com.bms.central_api_v1.exception.UnAuthorizedException;
import com.bms.central_api_v1.integration.AuthAPI;
import com.bms.central_api_v1.requestbody.SuccessResponseBody;
import com.bms.central_api_v1.responsebody.TokenResponseBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    AuthAPI authAPI;

    @Autowired
    ModelMapper mapper;

    public void verifyToken(String Authorization){
        try{
            SuccessResponseBody successResponseBody = authAPI.callVerifyTokenEndpoint(Authorization);
        }catch (Exception e){
            throw new UnAuthorizedException(String.format("Invalid token"));
        }
    }

    public String getToken(String email, String password){
        Object response = authAPI.callGenerateTokenEndpoint(email, password);
        TokenResponseBody tokenRB = mapper.map(response, TokenResponseBody.class);
        return tokenRB.getToken();
    }
}
