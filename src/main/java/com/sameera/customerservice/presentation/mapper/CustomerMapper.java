package com.sameera.customerservice.presentation.mapper;

import com.sameera.swagger.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    static CustomerMapper getInstance() { return  ModelMapperInstance.INSTANCE;}

    final class ModelMapperInstance {
        private static final CustomerMapper INSTANCE = Mappers
                .getMapper(CustomerMapper.class);
    }

    Customer toPresentationLayer(com.sameera.customerservice.domain.model.Customer customer);

    com.sameera.customerservice.domain.model.Customer toApplicationLayer(Customer customer);
}
