package com.bms.notification_v1_api.service;

import com.bms.notification_v1_api.model.AppUser;
import com.bms.notification_v1_api.model.Theather;
import com.bms.notification_v1_api.requestbody.AcceptTheatherRequestBody;
import com.bms.notification_v1_api.requestbody.TheatherRequestRB;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;

@Service
public class TheatherMailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

    @Value("${central.api.base}")
    String centralApiBaseUrl;

    public Context createContext(HashMap<String, String> map){
        Context context = new Context();
        for(String key : map.keySet()){
            context.setVariable(key, map.get(key));
        }
        return context;
    }


    public void notifyAdminForCreateTheatherRequest(TheatherRequestRB theatherRequestRB) throws MessagingException {
        Theather theather = theatherRequestRB.getTheather();
        AppUser admin = theatherRequestRB.getAdmin();
        String token = theatherRequestRB.getToken();
        Context context = new Context();
        context.setVariable("adminName", theatherRequestRB.getAdmin().getName());
        context.setVariable("theaterName", theatherRequestRB.getTheather().getName());
        context.setVariable("address", theatherRequestRB.getTheather().getAddress());
        context.setVariable("state", theatherRequestRB.getTheather().getState());
        context.setVariable("pincode", theatherRequestRB.getTheather().getPinCode());
        context.setVariable("ownerName", theatherRequestRB.getTheather().getOwner().getName());
        context.setVariable("ownerEmail", theatherRequestRB.getTheather().getOwner().getEmail());
        String acceptEndpoint = centralApiBaseUrl + "/theather/approve/" + theather.getId() + "/" + admin.getId() + "/" + token;
        context.setVariable("acceptEndpoint", acceptEndpoint);
        String htmlEmail = templateEngine.process("TheatherRequest", context);

        // MimeMessage ->
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);
        mimeMessageHelper.setSubject("A new theater has registered and is awaiting your approval.");
        mimeMessageHelper.setTo(theatherRequestRB.getAdmin().getEmail());
        mimeMessageHelper.setText(htmlEmail, true);
        javaMailSender.send(message);
    }

    public void sendTheatherAcceptanceMail(AcceptTheatherRequestBody acceptTheatherRequestBody) throws Exception{
        AppUser admin = acceptTheatherRequestBody.getAdmin();
        Theather theather = acceptTheatherRequestBody.getTheather();
        AppUser owner = acceptTheatherRequestBody.getTheather().getOwner();
        Context context = new Context();
        context.setVariable("ownerName", owner.getName());
        context.setVariable("adminName", admin.getName());
        context.setVariable("theatherName", theather.getName());
        context.setVariable("address", theather.getAddress());
        context.setVariable("state", theather.getState());
        context.setVariable("pincode", theather.getPinCode());
        String htmlEmail = templateEngine.process("AcceptTheatherRequest", context);
        // MimeMessage ->
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);
        mimeMessageHelper.setSubject("Theater Approval Confirmation");
        mimeMessageHelper.setTo(owner.getEmail());
        mimeMessageHelper.setText(htmlEmail, true);
        javaMailSender.send(message);
    }
}
