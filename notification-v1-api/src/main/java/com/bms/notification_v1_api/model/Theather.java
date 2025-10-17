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
public class Theather {
    UUID id;
    String name;
    String address;
    int pinCode;
    String state;
    AppUser owner;
    String status;
}
