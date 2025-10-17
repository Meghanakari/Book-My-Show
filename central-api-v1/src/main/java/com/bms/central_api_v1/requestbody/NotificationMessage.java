package com.bms.central_api_v1.requestbody;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotificationMessage {
    String messageType; // Create-user-notification
    Object payload; // AppUser Object
}
