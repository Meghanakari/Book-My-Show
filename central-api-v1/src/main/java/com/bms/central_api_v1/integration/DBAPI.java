package com.bms.central_api_v1.integration;

import com.bms.central_api_v1.models.*;
import com.bms.central_api_v1.requestbody.CreateTheatherRB;
import com.bms.central_api_v1.requestbody.CreateUserRB;
import com.bms.central_api_v1.responsebody.AdminsResponseBody;
import com.bms.central_api_v1.responsebody.ShowsByHallResposneBody;
import com.bms.central_api_v1.util.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class DBAPI extends RestAPI  {
    // This class will have different methods such that we make call to different endpoints of db api.
    // For example : You want to hit create user endpoint of dbapi so for that endpoint we will create one method.

    @Value("${db.api.base}")
    String baseUrl;

    @Autowired
    Mapper mapper;

    @Autowired
    ModelMapper modelMapper;

    public AppUser callCreateUserEndpoint(CreateUserRB createUserRB){
        AppUser appUser = mapper.mapCreateUserRBToAppUser(createUserRB);
        String endPoint = "/user/create";
        log.info("Calling /user/create endpoint of dbapi");
        Object resp = this.makePostCall(baseUrl, endPoint, appUser, new HashMap<>());
        AppUser userResp = modelMapper.map(resp, AppUser.class);
        return userResp;
    }

    public AppUser callGetUserByIdEndpoint(UUID userId){
        String endPoint = "/user/" + userId.toString();
        Object resp = this.makeGetCall(baseUrl, endPoint, new HashMap<>());
        if(resp == null){
            return null;
        }
        return modelMapper.map(resp, AppUser.class);
    }

    public Theather callCreateTheatherEndpoint(CreateTheatherRB theatherRB, AppUser owner){
        // we need to map theatherRB to Theather model object
        Theather theather = mapper.mapTheatherRBToTheatherModel(theatherRB, owner);
        String endPoint = "/theather/create";
        Object resp = this.makePostCall(baseUrl, endPoint, theather, new HashMap<>());
        return modelMapper.map(resp, Theather.class);
    }

    public List<AppUser> callGetAllAdminsEndpoint(){
        String endPoint = "/user/admins";
        Object resp = this.makeGetCall(baseUrl, endPoint, new HashMap<>());
        AdminsResponseBody allAdmins = modelMapper.map(resp, AdminsResponseBody.class);
        return allAdmins.getAdmins();
    }

    public Theather callGetTheatherById(UUID theatherId){
        String endPoint = "/theather/" + theatherId.toString();
        Object resp = this.makeGetCall(baseUrl, endPoint, new HashMap<>());
        return modelMapper.map(resp, Theather.class);
    }


    public Theather callUpdateTheatherEndPoint(Theather theather){
        String endPoint = "/theather/update";
        Object resp  =this.makePutCall(baseUrl, endPoint, theather, new HashMap<>());
        return modelMapper.map(resp, Theather.class);
    }

    public Hall callCreateHallEndpoint(Hall hall){
        String endPoint = "/hall/create";
        Object resp = this.makePostCall(baseUrl, endPoint, hall, new HashMap<>());
        return modelMapper.map(resp, Hall.class);
    }

    public Movie callCreateMovieEndpoint(Movie movie){
        String endPoint = "/movie/create";
        Object resp  = this.makePostCall(baseUrl, endPoint, movie, new HashMap<>());
        return modelMapper.map(resp, Movie.class);
    }

    public Hall callGetHallByIdEndpoint(UUID hallId){
        String endPoint = "/hall/"  + hallId.toString();
        Object resp = this.makeGetCall(baseUrl, endPoint, new HashMap<>());
        return modelMapper.map(resp, Hall.class);
    }

    public Movie callGetMovieByIdEndpoint(UUID movieId){
        String endPoint = "/movie/" + movieId.toString();
        Object resp = this.makeGetCall(baseUrl, endPoint, new HashMap<>());
        return modelMapper.map(resp, Movie.class);
    }

    public ShowsByHallResposneBody callGetShowsByHallId(UUID hallId){
        String endPoint = "/show/hall/" + hallId.toString();
        Object resp = this.makeGetCall(baseUrl, endPoint, new HashMap<>());
        return modelMapper.map(resp, ShowsByHallResposneBody.class);
    }

    public Show callCreateShowEndpoint(Show show){
        String endPoint = "/show/create";
        Object resp = this.makePostCall(baseUrl, endPoint,show, new HashMap<>());
        return modelMapper.map(resp, Show.class);
    }

    public Show callGetShowByShowId(UUID showId){
        String endPoint = "/show/" + showId.toString();
        Object resp = this.makeGetCall(baseUrl, endPoint, new HashMap<>());
        return modelMapper.map(resp, Show.class);
    }

    public BookedSeat callGetBookedSeatEndPoint(UUID showId, int seat){
        String endPoint = "/bookedseat/check";
        HashMap<String, String > queryParams = new HashMap<>();
        queryParams.put("showId", showId.toString());
        queryParams.put("seat", seat + "");
        Object resp = this.makeGetCall(baseUrl, endPoint, queryParams);
        if(resp == null){
            return null;
        }
        return modelMapper.map(resp, BookedSeat.class);
    }

    public void callCreateBookedSeatEndpoint(BookedSeat bookedSeat){
        String endPoint = "/bookedseat/create";
        this.makePostCall(baseUrl, endPoint, bookedSeat, new HashMap<>());
    }
}
