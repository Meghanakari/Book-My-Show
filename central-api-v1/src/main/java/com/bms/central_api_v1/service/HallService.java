package com.bms.central_api_v1.service;

import com.bms.central_api_v1.exception.UnAuthorizedException;
import com.bms.central_api_v1.integration.DBAPI;
import com.bms.central_api_v1.models.Hall;
import com.bms.central_api_v1.models.Theather;
import com.bms.central_api_v1.requestbody.CreateHallRB;
import com.bms.central_api_v1.requestbody.CreateTheatherRB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HallService {

    @Autowired
    TheatherService theatherService;

    @Autowired
    DBAPI dbapi;

    public Hall getHallById(UUID hallId){
        return dbapi.callGetHallByIdEndpoint(hallId);
    }

    public Hall createHall(UUID ownerId, UUID theatherId,
                           CreateHallRB hallRB){
        Theather theather  = theatherService.getTheatherById(theatherId);
        if(!theather.getOwner().getId().equals(ownerId)){
            throw new UnAuthorizedException(String.format(
                    "Theather with id %s does not own by owner with id %s"
            , theatherId.toString(), ownerId.toString()));
        }
        Hall hall = new Hall();
        hall.setCapacity(hallRB.getCapacity());
        hall.setName(hallRB.getName());
        hall.setTheather(theather);
        // Before returning you need to do something such that you will notify theather owner that hall got created
        return dbapi.callCreateHallEndpoint(hall);
    }
}
