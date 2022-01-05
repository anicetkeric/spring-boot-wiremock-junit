package com.wiremock.junit.billingservice.dto;

import com.wiremock.junit.billingservice.document.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceRequestDTO {

    private double amount;

    private LocalDateTime dueDate;

    @NotNull
    @NotBlank
    private String customerCode;

}
