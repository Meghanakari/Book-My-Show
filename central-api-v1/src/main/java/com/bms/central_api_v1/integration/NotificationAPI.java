package com.bms.central_api_v1.integration;

import com.bms.central_api_v1.requestbody.CreateTheatherNotificationRB;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class NotificationAPI extends RestAPI{

    // We have created notificationAPI class because work of notificationApi class (Central API) is to call not Notification api endpoint

    @Value("${notification.api.base}")
    String baseUrl;

    public void callNotifyAdminForTheatherRequestEndpoint(CreateTheatherNotificationRB theatherRequestRB){
        String endPoint = "/theather/request";
        this.makePutCall(baseUrl, endPoint, theatherRequestRB, new HashMap<>());
    }
}
