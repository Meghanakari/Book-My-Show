package com.bms.central_api_v1.controller;

import com.bms.central_api_v1.exception.UnAuthorizedException;
import com.bms.central_api_v1.models.Movie;
import com.bms.central_api_v1.requestbody.CreateMovieRB;
import com.bms.central_api_v1.responsebody.GeneralMessageResponse;
import com.bms.central_api_v1.service.AuthService;
import com.bms.central_api_v1.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/central/movie")
public class MovieController {

    @Autowired
    AuthService authService;

    @Autowired
    MovieService movieService;

    @PostMapping("/create")
    public ResponseEntity createMovie(@RequestParam UUID movieOwnerId,
                                      @RequestBody CreateMovieRB movieRB,
                                      @RequestHeader String Authorization){
        try{
            authService.verifyToken(Authorization);
            Movie movie = movieService.createMovie(movieRB, movieOwnerId);
            return new ResponseEntity(movie, HttpStatus.OK);
        }catch (UnAuthorizedException e){
            GeneralMessageResponse gm = new GeneralMessageResponse(e.getMessage());
            return new ResponseEntity(gm, HttpStatus.UNAUTHORIZED);
        }

    }
}
