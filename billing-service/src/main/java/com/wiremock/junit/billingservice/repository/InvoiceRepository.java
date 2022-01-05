package com.wiremock.junit.billingservice.repository;

import com.wiremock.junit.billingservice.document.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice, String> {
}
