package com.cinereserve.entity;

import com.cinereserve.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatType type;

    @ManyToOne
    private Screen screen;
}