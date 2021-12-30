package com.wiremock.junit.billingservice.document;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * <h2>Invoice</h2>
 *
 * Description: invoice
 */
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Document(collection = "invoice")
public class Invoice {

    @Id
    private String id;


    private LocalDateTime createdDate;

    private double amount;

    private Customer customer;
}
