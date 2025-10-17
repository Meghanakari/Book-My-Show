package com.bms.notification_v1_api.requestbody;

import com.bms.notification_v1_api.model.AppUser;
import com.bms.notification_v1_api.model.Theather;
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
