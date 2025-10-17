package com.bms.authentication_api_v1.Service;

import com.bms.authentication_api_v1.integration.DBAPI;
import com.bms.authentication_api_v1.model.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class AuthService {

    @Autowired
    DBAPI dbapi;

    @Value("${auth.secret.key}")
    String secretKey;

    Long expirationTime = 1000000L;

    public String generateToken(String userId, String password){
        String credentials = userId + ":" + password;
        String jwtToken = Jwts.builder().setSubject(credentials)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return jwtToken;
    }

    public String decryptToken(String token){
        String credentials = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return credentials;

    }


    public boolean verifyToken(String token){
        // decrypt the token
        String credentials = this.decryptToken(token);
        String email = credentials.split(":")[0];
        String password = credentials.split(":")[1];
        AppUser user  = dbapi.callGetUserByEmailEndpoint(email);
        if(user == null){
            return false;
        }
        if(user.getPassword().equals(password)){
            return true;
        }
        return false;
    }
}
