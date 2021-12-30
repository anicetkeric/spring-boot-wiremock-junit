package com.wiremock.junit.billingservice.document;

import lombok.Data;

@Data
public class Customer {
    private String id;
    private String name;
    private String email;
}