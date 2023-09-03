package com.sameera.customerservice.presentation.mapper;

import com.sameera.swagger.model.Address;
import com.sameera.swagger.model.Customer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit test for CustomerMapper")
public class CustomerMapperTest {

    private CustomerMapper customerMapper;

    @BeforeEach
    @DisplayName("Setup before each test")
    void setUp(){
        customerMapper = CustomerMapper.getInstance();
    }

    @Nested
    @DisplayName("Test toApplicationLayer method")
    class TestToApplication {

        @Test
        void givenSwaggerModel_whenToApplication_thenShouldMappedToApplication(){
            //given
            Address address = new Address();
            address.setCity("Munich");
            address.setState("Bavaria");
            address.setZip("100340");
            address.setStreet("main street");

            Customer customer = new Customer();
            customer.setFirstName("Sameera");
            customer.setLastName("Bandara");
            customer.setId(UUID.randomUUID());
            customer.setAddress(address);

            //when
            com.sameera.customerservice.domain.model.Customer customerDomain = customerMapper.toApplicationLayer(customer);

            //then
            Assertions.assertEquals(customer.getId(), customerDomain.getId());
            Assertions.assertEquals(customer.getFirstName(), customerDomain.getFirstName());
            Assertions.assertEquals(customer.getLastName(), customerDomain.getLastName());
            Assertions.assertEquals(customer.getAddress().getCity(), customerDomain.getAddress().getCity());
            Assertions.assertEquals(customer.getAddress().getStreet(), customerDomain.getAddress().getStreet());
            Assertions.assertEquals(customer.getAddress().getState(), customerDomain.getAddress().getState());
            Assertions.assertEquals(customer.getAddress().getZip(), customerDomain.getAddress().getZip());
        }
    }

    @Nested
    @DisplayName("Test toPresentationLayer method")
    class TestToPresentation {

        @Test
        void givenDomainModel_whenToPresentation_thenShouldMappedToPresentation(){
            //given
            com.sameera.customerservice.domain.model.Address address = com.sameera.customerservice.domain.model.Address
                    .builder()
                    .city("Munich")
                    .zip("100340")
                    .street("main street")
                    .state("Bavaria")
                    .build();

            com.sameera.customerservice.domain.model.Customer customerDomain = com.sameera.customerservice.domain.model.Customer
                    .builder()
                    .id(UUID.randomUUID())
                    .firstName("Sameera")
                    .lastName("Bandara")
                    .address(address)
                    .build();

            //when
            Customer customer = customerMapper.toPresentationLayer(customerDomain);

            //then
            Assertions.assertEquals(customerDomain.getId(), customer.getId());
            Assertions.assertEquals(customerDomain.getFirstName(), customer.getFirstName());
            Assertions.assertEquals(customerDomain.getLastName(), customer.getLastName());
            Assertions.assertEquals(customerDomain.getAddress().getCity(), customer.getAddress().getCity());
            Assertions.assertEquals(customerDomain.getAddress().getStreet(), customer.getAddress().getStreet());
            Assertions.assertEquals(customerDomain.getAddress().getState(), customer.getAddress().getState());
            Assertions.assertEquals(customerDomain.getAddress().getZip(), customer.getAddress().getZip());
        }
    }
}
