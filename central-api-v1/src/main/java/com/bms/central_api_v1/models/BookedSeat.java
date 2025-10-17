package com.bms.central_api_v1.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookedSeat {
    UUID id;
    UUID showId; // 1
    int seatNumber; // 1
}
