package com.bms.central_api_v1.requestbody;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateShowRequestBody {
    UUID hallId;
    UUID movieId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    Double price;
}
