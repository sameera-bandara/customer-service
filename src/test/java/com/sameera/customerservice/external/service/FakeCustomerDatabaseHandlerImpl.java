package com.sameera.customerservice.external.service;

import com.sameera.customerservice.domain.model.Customer;
import com.sameera.customerservice.domain.service.CustomerDatabaseHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FakeCustomerDatabaseHandlerImpl implements CustomerDatabaseHandler {
    private Map<UUID, Customer> customerMap = new HashMap<>();

    @Override
    public Customer save(Customer customer) {
        var customerId = UUID.randomUUID();

        customer.setId(customerId);
        return customer;
    }

    @Override
    public Customer find(UUID customerId) {
        Customer customer = customerMap.get(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException(String.format("No customer found for given id {}", customerId));
        }

        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        Customer existingCustomer = customerMap.get(customer.getId());
        if (existingCustomer == null) {
            throw new CustomerNotFoundException(String.format("No customer found for given id {}", customer.getId()));
        }

        customerMap.put(customer.getId(), customer);
        return customer;
    }

    public void addToCustomerMap(Map<UUID, Customer> customerMap) {
        this.customerMap.putAll(customerMap);
    }
}
