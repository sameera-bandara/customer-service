package com.sameera.customerservice.domain.service;

import com.sameera.customerservice.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDatabaseHandler databaseHandler;
    @Override
    public Customer saveCustomer(Customer customer) {
        return databaseHandler.save(customer);
    }

    @Override
    public Customer findCustomer(UUID customerId) {
        return databaseHandler.find(customerId);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return databaseHandler.update(customer);
    }
}
