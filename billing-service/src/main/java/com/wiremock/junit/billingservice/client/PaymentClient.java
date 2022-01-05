package com.wiremock.junit.billingservice.client;


import com.wiremock.junit.billingservice.document.Customer;
import com.wiremock.junit.billingservice.dto.PaymentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "paymentClient", url ="${service.api.client.payment}")
public interface PaymentClient {

    @PostMapping(path = "/payment")
    String savePayment(@RequestBody PaymentDTO paymentDTO);

}