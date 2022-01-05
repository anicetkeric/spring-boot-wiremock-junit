package com.example.paymentservice.controller;

import com.example.paymentservice.domain.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/api")
@Slf4j
public class PaymentController {

    @PostMapping(path = "/payment")
    public ResponseEntity<Void> save(@RequestBody Payment payment){

        if(payment.getAmount() > 0 || (payment.getCardExpiryDate().isAfter(LocalDate.now()))){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }

    }

}
