package com.sameera.customerservice.presentation.mapper;

import com.sameera.customerservice.domain.model.Address;
import com.sameera.customerservice.domain.model.Customer;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-03T13:08:41+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Azul Systems, Inc.)"
)
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public com.sameera.swagger.model.Customer toPresentationLayer(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        com.sameera.swagger.model.Customer customer1 = new com.sameera.swagger.model.Customer();

        customer1.setId( customer.getId() );
        customer1.setFirstName( customer.getFirstName() );
        customer1.setLastName( customer.getLastName() );
        customer1.setAddress( addressToAddress( customer.getAddress() ) );

        return customer1;
    }

    @Override
    public Customer toApplicationLayer(com.sameera.swagger.model.Customer customer) {
        if ( customer == null ) {
            return null;
        }

        Customer.CustomerBuilder customer1 = Customer.builder();

        customer1.id( customer.getId() );
        customer1.firstName( customer.getFirstName() );
        customer1.lastName( customer.getLastName() );
        customer1.address( addressToAddress1( customer.getAddress() ) );

        return customer1.build();
    }

    protected com.sameera.swagger.model.Address addressToAddress(Address address) {
        if ( address == null ) {
            return null;
        }

        com.sameera.swagger.model.Address address1 = new com.sameera.swagger.model.Address();

        address1.setStreet( address.getStreet() );
        address1.setCity( address.getCity() );
        address1.setState( address.getState() );
        address1.setZip( address.getZip() );

        return address1;
    }

    protected Address addressToAddress1(com.sameera.swagger.model.Address address) {
        if ( address == null ) {
            return null;
        }

        Address.AddressBuilder address1 = Address.builder();

        address1.street( address.getStreet() );
        address1.state( address.getState() );
        address1.city( address.getCity() );
        address1.zip( address.getZip() );

        return address1.build();
    }
}
