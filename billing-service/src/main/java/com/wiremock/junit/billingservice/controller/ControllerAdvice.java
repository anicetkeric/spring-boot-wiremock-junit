package com.wiremock.junit.billingservice.controller;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Handler Exception class. Manage response for all exception Class
 */
@Slf4j
@RestControllerAdvice
public class ControllerAdvice {
    /**
     * handle all feign exception
     * @param ex FeignException
     */
    @ExceptionHandler(value = { FeignException.class })
    protected ResponseEntity<?> handleFeignException(FeignException ex){
        return new ResponseEntity(HttpStatus.valueOf(ex.status()));
    }


}
