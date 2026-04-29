package com.cinereserve.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String role; // e.g., ROLE_USER, ROLE_ADMIN

    private String phone;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;
}