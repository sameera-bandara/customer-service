Feature: User need to able to add a customer
  Background:
    Given below customer details are already existing
      | CustomerId                              | FirstName | LastName  | State     | City    | Zip     | Street      |
      | e193e9cc-419b-47ad-a2a9-f510b0110cf1    | John      | Doe       | Bavaria   | Munich  | 100200  | 2nd street  |

 Scenario Outline: Customer Service receive an edit customer request with an existing customer id
    When a valid customer request received with customer "<CustomerId>", "<FirstName>", "<LastName>", "<State>", "<City>", "<Zip>", "<Street>"
    Then customer should be updated to the database and returned with last name updated to "Williams"
    Examples:
      | CustomerId                              | FirstName | LastName       | State     | City    | Zip     | Street      |
      | e193e9cc-419b-47ad-a2a9-f510b0110cf1    | John      | Williams       | Bavaria   | Munich  | 100200  | 2nd street  |

 Scenario Outline: Customer Service receive an edit customer request with invalid customer id
    When an invalid customer request received with "<CustomerId>", "<FirstName>", "<LastName>", "<State>", "<City>", "<Zip>", "<Street>"
    Then a customer not found exception should be thrown
   Examples:
     | CustomerId                              | FirstName | LastName       | State     | City    | Zip     | Street      |
     | 601f9d6b-07ea-4415-aad2-61eee7ff2f12    | John      | Williams       | Bavaria   | Munich  | 100200  | 2nd street  |