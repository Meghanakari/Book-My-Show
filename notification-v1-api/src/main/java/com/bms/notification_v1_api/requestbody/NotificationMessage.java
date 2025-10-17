package com.bms.notification_v1_api.requestbody;

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
