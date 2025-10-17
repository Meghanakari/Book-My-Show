package com.bms.dbapi.controller;

import com.bms.dbapi.models.Movie;
import com.bms.dbapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/api/v1/db/movie")
public class MovieController {


    @Autowired
    MovieRepository movieRepository;

    @PostMapping("/create")
    public ResponseEntity createMovie(@RequestBody Movie movie){
        movieRepository.save(movie);
        return new ResponseEntity(movie, HttpStatus.OK);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity getMovieById(@PathVariable UUID movieId){
        Movie movie = movieRepository.findById(movieId).orElse(null);
        return new ResponseEntity(movie, HttpStatus.OK);
    }

}
