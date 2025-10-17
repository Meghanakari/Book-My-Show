package com.bms.notification_v1_api.requestbody;

import com.bms.notification_v1_api.model.AppUser;
import com.bms.notification_v1_api.model.Theather;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TheatherRequestRB {
    Theather theather;
    AppUser admin;
    String token;
}
