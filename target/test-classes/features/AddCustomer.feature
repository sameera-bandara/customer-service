Feature: User need to able to add a customer

 Scenario Outline: Customer Service receive an add customer request
    When a valid customer request received with "<FirstName>", "<LastName>", "<State>", "<City>", "<Zip>", "<Street>"
    Then customer should be saved to the database and recieve response with unique customerId
    Examples:
     | FirstName      | LastName  | State     | City    | Zip     | Street      |
     | John           | Doe       | Bavaria   | Munich  | 100200  | 2nd street  |
     | Jim            | Renolds   | Georgia   | Georgia | 230435  | main street |

#better for co-pilot but
#  Scenario Outline: Customer Service receive an add customer request
#    When a valid customer request received with <FirstName>, <LastName>, <State>, <City>, <Zip>, <Street>
#    Then customer should be saved to the database
#    Examples:
#      | FirstName      | LastName  | State     | City    | Zip     | Street      |
#      | John           | Doe       | Bavaria   | Munich  | 100200  | 2nd street  |
#      | Jim            | Renolds   | Georgia   | Georgia | 230435  | main street |