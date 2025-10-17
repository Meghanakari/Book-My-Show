package com.bms.central_api_v1.models;

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
