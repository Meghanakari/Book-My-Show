package com.bms.central_api_v1.requestbody;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PurchaseTicketRequestBody {
    List<Integer> seats;
}
