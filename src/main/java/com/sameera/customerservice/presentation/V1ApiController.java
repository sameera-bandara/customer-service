package com.sameera.customerservice.presentation;

import com.sameera.customerservice.domain.service.CustomerService;
import com.sameera.customerservice.presentation.mapper.CustomerMapper;
import com.sameera.swagger.api.V1Api;
import com.sameera.swagger.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class V1ApiController implements V1Api {

    //customerService is injected by constructor, @AutoWired not required
    private final CustomerService customerService;
    private static final String LOG_START = "Start: {} -> {}";
    private static final String LOG_END = "End: {} -> {}";

    @Override
    @Transactional
    public ResponseEntity<Customer> findCustomer(UUID customerId) {
        log.info(LOG_START, "findCustomer", customerId);
        Customer customer = CustomerMapper.getInstance().toPresentationLayer(customerService.findCustomer(customerId));
        log.info(LOG_END, "findCustomer", HttpStatus.OK.value());

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Customer> addCustomer(Customer customer) {
        log.info(LOG_START, "addCustomer", customer);
        com.sameera.customerservice.domain.model.Customer customerModel = customerService.saveCustomer
                (CustomerMapper.getInstance().toApplicationLayer(customer));
        log.info(LOG_END, "addCustomer", HttpStatus.CREATED.value());

        return new ResponseEntity<>(CustomerMapper.getInstance().toPresentationLayer(customerModel), HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<Customer> updateCustomer(Customer customer) {
        log.info(LOG_START, "updateCustomer", customer);
        com.sameera.customerservice.domain.model.Customer customerModel = customerService.updateCustomer
                (CustomerMapper.getInstance().toApplicationLayer(customer));
        log.info(LOG_END, "updateCustomer", HttpStatus.OK.value());

        return new ResponseEntity<>(CustomerMapper.getInstance().toPresentationLayer(customerModel), HttpStatus.OK);
    }
}
