package com.bms.central_api_v1.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Show implements Comparable<Show>{
    UUID id;
    Hall hall;
    Movie movie;
    Long startTime;
    Long endTime;
    Double price;

    public int compareTo(Show show){
        return (int)(this.getStartTime() - show.getStartTime());
    }
}
