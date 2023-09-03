package features;

import com.sameera.customerservice.domain.model.Address;
import com.sameera.customerservice.domain.model.Customer;
import com.sameera.customerservice.domain.service.CustomerDatabaseHandler;
import com.sameera.customerservice.domain.service.CustomerService;
import com.sameera.customerservice.domain.service.CustomerServiceImpl;
import com.sameera.customerservice.external.service.FakeCustomerDatabaseHandlerImpl;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class EditCustomerTest {
    DataTable data;
    private CustomerDatabaseHandler customerDatabaseHandler;
    private CustomerService customerService;
    private Customer request;
    private Customer response;
    private RuntimeException customerNotFoundException;

    @Before
    public void setup() {
        customerDatabaseHandler = new FakeCustomerDatabaseHandlerImpl();
        customerService = new CustomerServiceImpl(customerDatabaseHandler);
    }

    @Given("below customer details are already existing")
    public void below_customer_details_are_already_existing(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> datalist = dataTable.asList((Type) List.class);

        Map<UUID, Customer> customerMap = datalist.subList(1, datalist.size()).stream().collect(Collectors.toMap(
                        it -> UUID.fromString(it.get(0)), it -> Customer
                                .builder()
                                .id(UUID.fromString(it.get(0)))
                                .firstName(it.get(1))
                                .lastName(it.get(2))
                                .address(
                                        Address.builder()
                                                .state(it.get(3))
                                                .city(it.get(4))
                                                .zip(it.get(5))
                                                .street(it.get(6))
                                                .build()
                                ).build()
                )
        );

        ((FakeCustomerDatabaseHandlerImpl) customerDatabaseHandler).addToCustomerMap(customerMap);
    }

    @When("a valid customer request received with customer {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void a_valid_customer_request_received_with_customer(String string, String string2, String string3, String string4, String string5, String string6, String string7) {
        request = Customer
                .builder()
                .id(UUID.fromString(string))
                .firstName(string2)
                .lastName(string3)
                .address(Address
                        .builder()
                        .state(string4)
                        .city(string5)
                        .zip(string6)
                        .street(string7)
                        .build())
                .build();

        response = customerService.updateCustomer(request);
    }

    @Then("customer should be updated to the database and returned with last name updated to {string}")
    public void customer_should_be_updated_to_the_database_and_returned_with_last_name_updated_to(String string) {
        Assertions.assertNotNull(response);
        Assertions.assertEquals(request, response);
    }

    @When("an invalid customer request received with {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void an_invalid_customer_request_received_with(String string, String string2, String string3, String string4, String string5, String string6, String string7) {
        request = Customer
                .builder()
                .firstName(string)
                .lastName(string2)
                .address(Address
                        .builder()
                        .state(string3)
                        .city(string4)
                        .zip(string5)
                        .street(string6)
                        .build())
                .build();

        try {
            response = customerService.updateCustomer(request);
        } catch (RuntimeException ex) {
            customerNotFoundException = ex;
        }

    }
    @Then("a customer not found exception should be thrown")
    public void a_customer_not_found_exception_should_be_thrown() {
        Assertions.assertNotNull(customerNotFoundException);
    }
}
