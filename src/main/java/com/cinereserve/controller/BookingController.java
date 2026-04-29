package com.cinereserve.controller;

import com.cinereserve.dto.BookingRequestDTO;
import com.cinereserve.entity.Booking;
import com.cinereserve.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public Booking createBooking(@Valid @RequestBody BookingRequestDTO request) {
        return bookingService.createBooking(
                request.getUserId(),
                request.getShowId(),
                request.getSeatIds()
        );
    }

}