Feature: Testing different request on the student application

  Scenario: Check if the student application can be accessed by users
    When User sends a GET request to list endpoint
    Then User must get back a valid status code 200

  Scenario Outline: Create a new student & verify if the student is added
    When I create a new student by providing the information "<firstName>" "<lastName>" "<email>" "<programme>" "<courses>"
    Then I verify that the student with lastName is created
    Examples:
      | firstName | lastName | email               | programme        | courses |
      | pearl     | Pat      | pearlpat@gmail.com  | Computer Science | JAVA    |
      | tweety    | Sha      | tweetysha@gmail.com | Api Testing      | Python  |

  Scenario: check if the lastName of new student has been updated and verify updated information
    When New student is updated with new "<firstname>" "<lastName>" "<email>" "<programme>" "<courses>"


  Scenario: check if the student has been deleted by id and verify if deleted
    When I have deleted student by id
    Then I verify that student is deleted







