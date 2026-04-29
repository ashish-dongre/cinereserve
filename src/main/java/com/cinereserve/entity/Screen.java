package com.cinereserve.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Screen 1, Screen 2

    private Integer totalSeats;

    @ManyToOne
    private Theatre theatre;

    @OneToMany(mappedBy = "screen")
    private List<Seat> seats;
}