package com.wiremock.junit.billingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {
    private String invoiceId;
    private String cardNumber;
    private LocalDate cardExpiryDate;
    private Double amount;
}
