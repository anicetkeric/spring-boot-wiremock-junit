package com.wiremock.junit.billingservice.dto;

import com.wiremock.junit.billingservice.document.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {

    @Id
    private String id;

    @NotNull
    private String number;

    private LocalDateTime createdDate;

    private double amount;

    private Customer customer;
}
