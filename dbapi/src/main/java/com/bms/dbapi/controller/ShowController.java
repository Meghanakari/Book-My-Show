package com.bms.dbapi.controller;

import com.bms.dbapi.models.Show;
import com.bms.dbapi.repository.ShowRepository;
import com.bms.dbapi.responsebody.ShowsByHallResposneBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/show")
public class ShowController {

    @Autowired
    ShowRepository showRepository;

    @PostMapping("/create")
    public ResponseEntity createShow(@RequestBody Show show){
        showRepository.save(show);
        return new ResponseEntity(show, HttpStatus.CREATED);
    }

    @GetMapping("/hall/{hallId}")
    public ResponseEntity getShowsByHallId(@PathVariable UUID hallId){
        List<Show> shows = showRepository.getShowsByHallId(hallId);
        ShowsByHallResposneBody showsByHallResposneBody = new ShowsByHallResposneBody();
        showsByHallResposneBody.setShows(shows);
        return new ResponseEntity(showsByHallResposneBody, HttpStatus.OK);
    }

    @GetMapping("/{showId}")
    public ResponseEntity getShowByShowId(@PathVariable UUID showId){
        Show show = showRepository.findById(showId).orElse(null);
        return new ResponseEntity(show, HttpStatus.OK);
    }
}
