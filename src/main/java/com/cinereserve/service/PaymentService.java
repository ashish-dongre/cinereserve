package com.cinereserve.service;

import com.cinereserve.dto.PaymentRequest;
import com.cinereserve.dto.PaymentResponse;
import com.cinereserve.entity.Booking;
import com.cinereserve.enums.BookingStatus;
import com.cinereserve.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private BookingRepository bookingRepository;

    @Transactional
    public PaymentResponse processPayment(PaymentRequest request) {
        // Mock payment processing logic
        boolean paymentSuccess = Math.random() > 0.1; // 90% success rate

        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (paymentSuccess) {
            booking.setStatus(BookingStatus.CONFIRMED);
            bookingRepository.save(booking);
            return new PaymentResponse(UUID.randomUUID().toString(), "SUCCESS", "Payment processed successfully");
        } else {
            booking.setStatus(BookingStatus.CANCELLED);
            bookingRepository.save(booking);
            return new PaymentResponse(null, "FAILED", "Payment failed");
        }
    }
}
