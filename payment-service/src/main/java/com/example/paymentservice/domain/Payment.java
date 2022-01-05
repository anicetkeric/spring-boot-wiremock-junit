package com.example.paymentservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private String customerNumber;
    private String cardNumber;
    private LocalDate cardExpiryDate;
    private Double amount;
}
