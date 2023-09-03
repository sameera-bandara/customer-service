package com.sameera.customerservice.external.service;

import com.sameera.customerservice.domain.model.Customer;
import com.sameera.customerservice.domain.service.CustomerDatabaseHandler;
import com.sameera.customerservice.external.entity.CustomerEntity;
import com.sameera.customerservice.external.mapper.CustomerMapper;
import com.sameera.customerservice.external.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerDatabaseHandlerImpl implements CustomerDatabaseHandler {

    private final CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        log.info("{}", "saving customer");
        CustomerEntity customerEntity = customerRepository.save(CustomerMapper.getInstance().toExternalLayer(customer));

        return CustomerMapper.getInstance().toApplicationLayer(customerEntity);
    }

    @Override
    public Customer find(UUID customerId) {
        CustomerEntity customerEntity = customerRepository.findById(customerId).orElse(new CustomerEntity());

        if (customerEntity.getId() == null) {
            throw new CustomerNotFoundException(String.format("No customer found for given id {}", customerId));
        }

        return CustomerMapper.getInstance().toApplicationLayer(customerEntity);
    }

    @Override
    public Customer update(Customer customer) {

        CustomerEntity customerEntity = customerRepository.findById(customer.getId()).get();

        if (customerEntity == null) {
            throw new CustomerNotFoundException(String.format("No customer found for given id {}", customer.getId()));
        }

        CustomerEntity newCustomerEntity = CustomerMapper.getInstance().toExternalLayer(customer);

        customerEntity.setFirstName(newCustomerEntity.getFirstName());
        customerEntity.setLastName(newCustomerEntity.getLastName());
        customerEntity.setAddress(newCustomerEntity.getAddress());

        return CustomerMapper.getInstance().toApplicationLayer(customerEntity);
    }


}
