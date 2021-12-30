package com.example.customerservice.rest;

import com.example.customerservice.domain.Customer;
import com.example.customerservice.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/customers")
    public List<Customer> customers(){
        return customerService.getCustomers();
    }
    @GetMapping(path = "/customers/{id}")
    public Customer customerById(@PathVariable int id){
        return customerService.getCustomer(id);
    }

}
