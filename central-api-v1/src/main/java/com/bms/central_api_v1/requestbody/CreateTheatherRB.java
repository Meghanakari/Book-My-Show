package com.bms.central_api_v1.requestbody;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateTheatherRB {
    String name;
    String address;
    int pinCode;
    String state;
}
