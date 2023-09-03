package features;

import com.sameera.customerservice.domain.model.Address;
import com.sameera.customerservice.domain.model.Customer;
import com.sameera.customerservice.domain.service.CustomerDatabaseHandler;
import com.sameera.customerservice.domain.service.CustomerService;
import com.sameera.customerservice.domain.service.CustomerServiceImpl;
import com.sameera.customerservice.external.service.FakeCustomerDatabaseHandlerImpl;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;


public class AddCustomerTest {

    private CustomerDatabaseHandler customerDatabaseHandler;
    private CustomerService customerService;
    private Customer response;
    private Customer request;

    @Before
    public void setup() {
        customerDatabaseHandler = new FakeCustomerDatabaseHandlerImpl();
        customerService = new CustomerServiceImpl(customerDatabaseHandler);
    }

    @When("a valid customer request received with {string}, {string}, {string}, {string}, {string}, {string}")
    public void a_valid_customer_request_received_with(String string, String string2, String string3, String string4, String string5, String string6) {
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

        response = customerService.saveCustomer(request);
    }



    @Then("customer should be saved to the database and recieve response with unique customerId")
    public void customer_should_be_saved_to_the_database_and_recieve_response_with_unique_customer_id() {
        Assertions.assertNotNull(response.getId());
        Assertions.assertEquals(request.getLastName(), response.getLastName());
        Assertions.assertEquals(request.getFirstName(), response.getFirstName());
        Assertions.assertEquals(request.getAddress(), response.getAddress());
    }
}
