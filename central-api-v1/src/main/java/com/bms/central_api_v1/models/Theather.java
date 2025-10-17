package com.bms.central_api_v1.models;

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
