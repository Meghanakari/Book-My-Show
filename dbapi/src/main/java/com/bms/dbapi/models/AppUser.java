package com.bms.dbapi.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id; // Base64
    String name;
    @Column(unique = true)
    String email;
    String password;
    @Column(unique = true)
    Long phoneNumber;
    String address;
    String state;
    int pincode;
    String userType;
}
