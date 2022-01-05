package com.wiremock.junit.billingservice.document;

import lombok.Data;

@Data
public class Customer {
    private int id;
    private String code;
    private String firstname;
    private String lastname;
    private String email;
}