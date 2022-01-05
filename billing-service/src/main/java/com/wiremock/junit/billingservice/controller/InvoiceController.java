package com.wiremock.junit.billingservice.controller;

import com.wiremock.junit.billingservice.client.CustomerClient;
import com.wiremock.junit.billingservice.document.Customer;
import com.wiremock.junit.billingservice.document.Invoice;
import com.wiremock.junit.billingservice.dto.InvoicePostDTO;
import com.wiremock.junit.billingservice.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/invoice")
@Slf4j
public class InvoiceController {

    private final InvoiceService invoiceService;

    private final CustomerClient customerClient;

    public InvoiceController(InvoiceService invoiceService, CustomerClient customerClient) {
        this.invoiceService = invoiceService;
        this.customerClient = customerClient;
    }

    @PostMapping
    public ResponseEntity<Invoice> create(@RequestBody InvoicePostDTO invoicePost) {

        Customer customer = customerClient.customerById(invoicePost.getCustomerCode());
        if (customer != null){
            Invoice invoice = Invoice.builder()
                    .dueDate(invoicePost.getDueDate())
                    .amount(invoicePost.getAmount())
                    .createdDate(LocalDateTime.now())
                    .customer(customer)
                    .number(RandomStringUtils.randomAlphanumeric(10))
                    .build();
            Invoice invoiceCreated = invoiceService.save(invoice);
            return new ResponseEntity<>(invoiceCreated, HttpStatus.CREATED);
        }
        throw new RuntimeException("Customer not found");
    }



}
