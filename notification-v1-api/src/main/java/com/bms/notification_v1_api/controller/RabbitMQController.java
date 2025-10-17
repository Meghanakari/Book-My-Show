package com.bms.notification_v1_api.controller;

import com.bms.notification_v1_api.requestbody.AcceptTheatherRequestBody;
import com.bms.notification_v1_api.requestbody.NotificationMessage;
import com.bms.notification_v1_api.requestbody.TheatherRequestRB;
import com.bms.notification_v1_api.service.TheatherMailService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {

    @Autowired
    ModelMapper mapper;

    @Autowired
    TheatherMailService theatherMailService;

    @RabbitListener(queues = "bms-notification-queue")
    public void consumeMessage(@Payload NotificationMessage notificationMessage) throws Exception{

        String messageType = notificationMessage.getMessageType();
        if(messageType.equals("create_theather")){
            Object payload = notificationMessage.getPayload();
            TheatherRequestRB theatherRequestRB = mapper.map(payload, TheatherRequestRB.class);
            theatherMailService.notifyAdminForCreateTheatherRequest(theatherRequestRB);
            // we to map the payload to requested type
        }else if(messageType.equals("create_user")){
        }else if(messageType.equals("notify_user_bill")){
        }else if(messageType.equals("THEATHER_ACCEPTANCE")){
            Object payload = notificationMessage.getPayload();
            // we need to convert payload to desired request body
            AcceptTheatherRequestBody rb = mapper.map(payload, AcceptTheatherRequestBody.class);
            // service
            theatherMailService.sendTheatherAcceptanceMail(rb);
        }
    }


}
