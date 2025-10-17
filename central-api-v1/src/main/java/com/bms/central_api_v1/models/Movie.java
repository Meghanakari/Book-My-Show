package com.bms.central_api_v1.models;

import lombok.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movie {
    UUID id;
    String name;
    Double duration; // it will be in hours
    boolean isReleased;
    AppUser movieOwner;
    int review;
    int totalReviewVotes;
    String language;
}
