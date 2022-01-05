package com.wiremock.junit.billingservice.service;


import com.wiremock.junit.billingservice.document.Invoice;

import java.util.Optional;

public interface InvoiceService {

    Invoice save(Invoice invoice);

    Optional<Invoice> getById(String invoiceID);
}