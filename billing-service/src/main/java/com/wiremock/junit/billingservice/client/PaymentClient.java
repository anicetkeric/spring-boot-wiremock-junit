package com.wiremock.junit.billingservice.client;


import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "paymentClient", url ="${service.api.client.payment}")
public interface PaymentClient {


}