package com.sameera.customerservice.external.mapper;

import com.sameera.customerservice.domain.model.Address;
import com.sameera.customerservice.domain.model.Customer;
import com.sameera.customerservice.external.entity.AddressEntity;
import com.sameera.customerservice.external.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    static CustomerMapper getInstance() { return  Mappers.getMapper(CustomerMapper.class);}

    CustomerEntity toExternalLayer(Customer customer);

    Customer toApplicationLayer(CustomerEntity customerEntity);
}
