package com.cinereserve.entity;

import com.cinereserve.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime bookingTime;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private Integer totalAmount;

    @ManyToOne
    private User user;

    @ManyToOne
    private Show show;

    @OneToMany(mappedBy = "booking")
    private List<BookingSeat> bookingSeats;
}