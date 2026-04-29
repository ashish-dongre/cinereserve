package com.cinereserve.service;

import com.cinereserve.entity.*;
import com.cinereserve.enums.BookingStatus;
import com.cinereserve.enums.SeatStatus;
import com.cinereserve.repository.BookingRepository;
import com.cinereserve.repository.ShowRepository;
import com.cinereserve.repository.ShowSeatRepository;
import com.cinereserve.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final ShowSeatRepository showSeatRepository;
    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;
    private final UserRepository userRepository;

    @Transactional
    public Booking createBooking(Long userId, Long showId, List<Long> seatIds) {

        // 1. Fetch user & show
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found"));

        // 2. Fetch seats
        List<ShowSeat> seats = showSeatRepository
                .findByShowIdAndIdIn(showId, seatIds);

        // 3. Check availability
        for (ShowSeat seat : seats) {
            if (seat.getStatus() != SeatStatus.AVAILABLE) {
                throw new RuntimeException("Seat already booked!");
            }
        }

        // 4. Lock seats
        for (ShowSeat seat : seats) {
            seat.setStatus(SeatStatus.LOCKED);
        }

        // 5. Create booking
        Booking booking = Booking.builder()
                .user(user)
                .show(show)
                .status(BookingStatus.PENDING)
                .bookingTime(LocalDateTime.now())
                .build();

        booking = bookingRepository.save(booking);

        // 6. Map seats
        List<BookingSeat> bookingSeats = new ArrayList<>();
        int totalAmount = 0;

        for (ShowSeat seat : seats) {
            BookingSeat bs = BookingSeat.builder()
                    .booking(booking)
                    .showSeat(seat)
                    .build();

            bookingSeats.add(bs);
            totalAmount += seat.getPrice();
        }

        booking.setBookingSeats(bookingSeats);
        booking.setTotalAmount(totalAmount);

        return bookingRepository.save(booking);
    }
    @Transactional
    public void confirmBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow();

        for (BookingSeat bs : booking.getBookingSeats()) {
            bs.getShowSeat().setStatus(SeatStatus.BOOKED);
        }

        booking.setStatus(BookingStatus.CONFIRMED);
    }
}