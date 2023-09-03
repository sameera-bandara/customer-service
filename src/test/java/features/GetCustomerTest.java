package features;

import com.sameera.customerservice.domain.model.Address;
import com.sameera.customerservice.domain.model.Customer;
import com.sameera.customerservice.domain.service.CustomerDatabaseHandler;
import com.sameera.customerservice.domain.service.CustomerService;
import com.sameera.customerservice.domain.service.CustomerServiceImpl;
import com.sameera.customerservice.external.service.CustomerNotFoundException;
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

public class GetCustomerTest {
    DataTable data;
    private CustomerDatabaseHandler customerDatabaseHandler;
    private CustomerService customerService;
    private Customer response;
    private RuntimeException customerNotFoundException;

    @Before
    public void setup() {
        customerDatabaseHandler = new FakeCustomerDatabaseHandlerImpl();
        customerService = new CustomerServiceImpl(customerDatabaseHandler);
    }

    @Given("below customer details in the database")
    public void below_customer_details_in_the_database(io.cucumber.datatable.DataTable dataTable) {
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

    @When("a valid customer request received with {string}")
    public void a_valid_customer_request_received_with(String string) {
        response = customerService.findCustomer(UUID.fromString(string));
    }

    @Then("customer should be returned with customer id {string}")
    public void customer_should_be_returned_with_customer_id(String string) {
        Assertions.assertEquals(UUID.fromString(string), response.getId());
    }


    @When("a invalid customer request received with {string}")
    public void a_invalid_customer_request_received_with(String string) {
        try {
            response = customerService.findCustomer(UUID.fromString(string));
        } catch (CustomerNotFoundException ex) {
            customerNotFoundException = ex;
        }

    }

    @Then("customer should not be returned")
    public void customer_should_not_be_returned() {
        Assertions.assertNotNull(customerNotFoundException);
    }

}
