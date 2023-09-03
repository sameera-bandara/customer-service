package com.sameera.customerservice.domain.service;

import com.sameera.customerservice.domain.model.Customer;

import java.util.UUID;

public interface CustomerDatabaseHandler {
    Customer save(Customer customer);

    Customer find(UUID customerId);

    Customer update(Customer customer);
}
