package com.sameera.customerservice.external.mapper;

import com.sameera.customerservice.domain.model.Address;
import com.sameera.customerservice.domain.model.Customer;
import com.sameera.customerservice.external.entity.AddressEntity;
import com.sameera.customerservice.external.entity.CustomerEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-03T13:08:41+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Azul Systems, Inc.)"
)
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerEntity toExternalLayer(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setId( customer.getId() );
        customerEntity.setFirstName( customer.getFirstName() );
        customerEntity.setLastName( customer.getLastName() );
        customerEntity.setAddress( addressToAddressEntity( customer.getAddress() ) );

        return customerEntity;
    }

    @Override
    public Customer toApplicationLayer(CustomerEntity customerEntity) {
        if ( customerEntity == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.id( customerEntity.getId() );
        customer.firstName( customerEntity.getFirstName() );
        customer.lastName( customerEntity.getLastName() );
        customer.address( addressEntityToAddress( customerEntity.getAddress() ) );

        return customer.build();
    }

    protected AddressEntity addressToAddressEntity(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressEntity addressEntity = new AddressEntity();

        addressEntity.setStreet( address.getStreet() );
        addressEntity.setState( address.getState() );
        addressEntity.setCity( address.getCity() );
        addressEntity.setZip( address.getZip() );

        return addressEntity;
    }

    protected Address addressEntityToAddress(AddressEntity addressEntity) {
        if ( addressEntity == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.street( addressEntity.getStreet() );
        address.state( addressEntity.getState() );
        address.city( addressEntity.getCity() );
        address.zip( addressEntity.getZip() );

        return address.build();
    }
}
