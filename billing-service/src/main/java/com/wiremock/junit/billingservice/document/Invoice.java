package com.wiremock.junit.billingservice.document;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <h2>Invoice</h2>
 *
 * Description: invoice
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Document(collection = "invoice")
public class Invoice {

    @Id
    private String id;

    @NotNull
    private String number;

    private LocalDateTime createdDate;

    private LocalDateTime dueDate;

    private double amount;

    private Customer customer;

    private List<String> paymentIds;
}
