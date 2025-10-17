package com.bms.central_api_v1.util;

import com.bms.central_api_v1.enums.TheatherStatus;
import com.bms.central_api_v1.models.AppUser;
import com.bms.central_api_v1.models.Theather;
import com.bms.central_api_v1.requestbody.CreateTheatherRB;
import com.bms.central_api_v1.requestbody.CreateUserRB;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Mapper {

    public AppUser mapCreateUserRBToAppUser(CreateUserRB createUserRB){
        AppUser appUser = new AppUser();
        appUser.setName(createUserRB.getName());
        appUser.setEmail(createUserRB.getEmail());
        appUser.setPassword(createUserRB.getPassword());
        appUser.setPincode(createUserRB.getPinCode());
        appUser.setAddress(createUserRB.getAddress());
        appUser.setState(createUserRB.getState());
        appUser.setPhoneNumber(createUserRB.getPhoneNumber());
        appUser.setUserType(createUserRB.getUserType().toString());
        return appUser;
    }

    public Theather mapTheatherRBToTheatherModel(CreateTheatherRB theatherRB, AppUser owner){
        Theather theather = new Theather();
        theather.setAddress(theather.getAddress());
        theather.setOwner(owner);
        theather.setStatus(TheatherStatus.REQUEST_RAISED.toString());
        theather.setPinCode(theather.getPinCode());
        theather.setState(theatherRB.getState());
        theather.setName(theatherRB.getName());
        return theather;
    }

}
