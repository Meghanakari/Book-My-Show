package com.bms.central_api_v1.requestbody;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateHallRB {
    String name;
    int capacity;
}
