package com.example.customerservice.service;

import com.example.customerservice.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    List<Customer> customerList = List.of(
            new Customer(1,"Smart","Philip", "p.smart@gmail.com"),
            new Customer(2,"Boss","Eric", "b.eric@gmail.com")
    );

   public List<Customer> getCustomers(){
        return customerList;
    }
   public Customer getCustomer(int id){
        return customerList.stream().filter(c -> c.getId() == id).findFirst().get();
    }
}
