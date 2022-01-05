package com.wiremock.junit.billingservice.controller;

import com.wiremock.junit.billingservice.client.CustomerClient;
import com.wiremock.junit.billingservice.client.PaymentClient;
import com.wiremock.junit.billingservice.document.Customer;
import com.wiremock.junit.billingservice.document.Invoice;
import com.wiremock.junit.billingservice.dto.InvoiceRequestDTO;
import com.wiremock.junit.billingservice.dto.PaymentDTO;
import com.wiremock.junit.billingservice.dto.PaymentRequestDTO;
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
import java.util.Optional;

@RestController
@RequestMapping(path = "/invoice")
@Slf4j
public class InvoiceController {

    private final InvoiceService invoiceService;

    private final PaymentClient paymentClient;
    private final CustomerClient customerClient;

    public InvoiceController(InvoiceService invoiceService, PaymentClient paymentClient, CustomerClient customerClient) {
        this.invoiceService = invoiceService;
        this.paymentClient = paymentClient;
        this.customerClient = customerClient;
    }

    @PostMapping
    public ResponseEntity<Invoice> create(@RequestBody InvoiceRequestDTO invoicePost) {

        Customer customer = customerClient.customerByCode(invoicePost.getCustomerCode());
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

    @PostMapping("/payment")
    public ResponseEntity<Void> savePayment(@RequestBody PaymentRequestDTO paymentRequestDTO) {

       Optional<Invoice> invoiceOptional = invoiceService.getById(paymentRequestDTO.getInvoiceId());
        if (invoiceOptional.isPresent()){
            Invoice invoice =  invoiceOptional.get();

            PaymentDTO paymentDTO = PaymentDTO.builder()
                    .cardExpiryDate(paymentRequestDTO.getCardExpiryDate())
                    .cardNumber(paymentRequestDTO.getCardNumber())
                    .customerNumber(invoice.getCustomer().getCode())
                    .amount(paymentRequestDTO.getAmount())
                    .build();

            String paymentId = paymentClient.savePayment(paymentDTO);

            invoice.getPaymentIds().add(paymentId);

            invoiceService.save(invoice);

            return new ResponseEntity<>(HttpStatus.CREATED);

        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
