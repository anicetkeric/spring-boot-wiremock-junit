package com.wiremock.junit.billingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private String customerNumber;
    private String cardNumber;
    private LocalDate cardExpiryDate;
    private Double amount;
}
