package com.sameera.customerservice.external.service;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
