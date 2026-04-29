package com.cinereserve.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private Long bookingId;
    private Double amount;
    private String paymentMethod; // e.g., CARD, UPI
}
