package com.bms.central_api_v1.service;

import com.bms.central_api_v1.integration.DBAPI;
import com.bms.central_api_v1.models.AppUser;
import com.bms.central_api_v1.models.Movie;
import com.bms.central_api_v1.requestbody.CreateMovieRB;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MovieService {

    @Autowired
    UserService userService;

    @Autowired
    DBAPI dbapi;

    public Movie getMovieById(UUID movieId){
        return dbapi.callGetMovieByIdEndpoint(movieId);
    }

    public Movie createMovie(CreateMovieRB movieRB, UUID movieOwnerId){
        // You need to do some validation that this movieOwnerId is having userType as movie owner or not
        AppUser movieOwner = userService.getUserById(movieOwnerId);
        Movie movie = new Movie();
        movie.setMovieOwner(movieOwner);
        movie.setName(movieRB.getName());
        movie.setDuration(movieRB.getDuration());
        movie.setLanguage(movieRB.getLanguage());
        movie.setReleased(movieRB.isReleased());
        movie.setReview(0);
        movie.setTotalReviewVotes(0);
        // db api class to call create movie endpoint of database api
        // you need to call notification api to notify movie owners regarding movie creation
        return dbapi.callCreateMovieEndpoint(movie);
    }
}
