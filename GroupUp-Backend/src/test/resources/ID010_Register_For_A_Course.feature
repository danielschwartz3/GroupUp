Feature: Register for a course
  
  As a Student
  I would like to register for a course within the GroupUp sytem 
  So that I can join the conversation with other student.

  Scenario Outline: A valid user attemps register for a course (Normal Flow)
    Given valid email <email> and password <password>
    And the course <course> already exist in the system
    When user <email> requests register for course <course>
    Then the user will be registered under the course

    Examples: 
      | email                 | password | course   |
      | bw@mail.mcgill.ca     | aslkda   | ECSE-428 |
      | bweiss@mail.mcgill.ca | aasa     | FACC-400 |
      | rs@carleton.ca        | fire     | COMP-360 |

  Scenario Outline: User attempts to register for a course that does not exist (Error Flow)
    Given valid email <email> and password <password>
    And the course <course> doesn't exist
    When user <user_name> requests register for course <course>
    Then the user will be notified that the course does not exist

    Examples: 
      | email                 | password | course   |
      | bw@mail.mcgill.ca     | aslkda   | ECSE-428 |
      | bweiss@mail.mcgill.ca | aasa     | FACC-400 |
      | rs@carleton.ca        | fire     | COMP-360 |
