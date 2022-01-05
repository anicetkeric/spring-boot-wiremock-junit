package com.wiremock.junit.billingservice.service;


import com.wiremock.junit.billingservice.document.Invoice;

public interface InvoiceService {

    Invoice save(Invoice invoice);
}