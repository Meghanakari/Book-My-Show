package com.bms.dbapi.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "bookedseats")
public class BookedSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    UUID showId; // 1
    int seatNumber; // 1
}
