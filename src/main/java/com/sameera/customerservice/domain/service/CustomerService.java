package com.sameera.customerservice.domain.service;

import com.sameera.customerservice.domain.model.Customer;

import java.util.UUID;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    Customer findCustomer(UUID customerId);

    Customer updateCustomer(Customer customer);
}
