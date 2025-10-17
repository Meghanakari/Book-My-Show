package com.bms.notification_v1_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    UUID id;
    String name;
    String email;
    String password;
    Long phoneNumber;
    String address;
    String state;
    int pincode;
    String userType;
}
