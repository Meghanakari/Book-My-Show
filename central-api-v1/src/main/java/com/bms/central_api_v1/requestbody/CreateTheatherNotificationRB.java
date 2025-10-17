package com.bms.central_api_v1.requestbody;

import com.bms.central_api_v1.models.AppUser;
import com.bms.central_api_v1.models.Theather;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTheatherNotificationRB {
    Theather theather;
    AppUser admin;
    String token;
}
