package com.bms.authentication_api_v1.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppUser {
    UUID id; // Base64
    String name;
    String email;
    String password;
    Long phoneNumber;
    String address;
    String state;
    int pincode;
    String userType;
}
