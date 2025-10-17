package com.bms.central_api_v1.responsebody;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BillResponseBody {
    String userName;
    String movieName;
    LocalDateTime startTime;
    LocalDateTime endTime;
    List<Integer> seats;
    Double totalAmount;
}
