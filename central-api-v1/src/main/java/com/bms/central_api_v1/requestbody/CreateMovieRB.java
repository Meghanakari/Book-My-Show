package com.bms.central_api_v1.requestbody;

import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class CreateMovieRB {
    String name;
    Double duration; // it will be in hours
    boolean isReleased;
    String language;
}
