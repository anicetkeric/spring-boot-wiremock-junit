package com.wiremock.junit.billingservice.client;


import com.wiremock.junit.billingservice.document.Customer;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "customerClient", url ="${service.api.client.customer}")
public interface CustomerClient {

    @GetMapping(path="/customers/{id}")
    Customer customerById(@PathVariable String id);

    @GetMapping(path="/customers/code/{code}")
    Customer customerByCode(@PathVariable String code);

    @GetMapping(path="/customers")
    List<Customer> customers();

}