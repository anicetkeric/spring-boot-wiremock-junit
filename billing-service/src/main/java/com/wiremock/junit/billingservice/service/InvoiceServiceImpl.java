package com.wiremock.junit.billingservice.service;

import com.wiremock.junit.billingservice.document.Invoice;
import com.wiremock.junit.billingservice.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
    @Override
    public Optional<Invoice> getById(String invoiceID) {
        return invoiceRepository.findById(invoiceID);
    }
}