Feature: User need to able to retrieve a customer
  Background:
    Given below customer details in the database
      | CustomerId                              | FirstName | LastName  | State     | City    | Zip     | Street      |
      | e193e9cc-419b-47ad-a2a9-f510b0110cf1    | John      | Doe       | Bavaria   | Munich  | 100200  | 2nd street  |


 Scenario: Customer Service receive get customer request
    When a valid customer request received with "e193e9cc-419b-47ad-a2a9-f510b0110cf1"
    Then customer should be returned with customer id "e193e9cc-419b-47ad-a2a9-f510b0110cf1"

  Scenario: Customer Service receive get customer request
    When a invalid customer request received with "601f9d6b-07ea-4415-aad2-61eee7ff2f12"
    Then customer should not be returned