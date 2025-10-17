package com.bms.central_api_v1.requestbody;

import com.bms.central_api_v1.models.AppUser;
import com.bms.central_api_v1.models.Theather;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AcceptTheatherRequestBody {
    Theather theather;
    AppUser admin;
}
