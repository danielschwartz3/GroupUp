Feature: Register for a course
  
  As a Student
  I would like to register for a course within the GroupUp sytem 
  So that I can join the conversation with other student.

  Scenario Outline: A valid user attemps register for a course (Normal Flow)
    Given valid email <email> and password <password>
    Given the course <course> exist
    And the user is logged in
    When user <email> requests register for course <course>
    Then the user will be registered undered the course
      | user_name             | password | course   |
      | bw@mail.mcgill.ca     | aslkda   | ECSE-428 |
      | bweiss@mail.mcgill.ca | aasa     | FACC-400 |
      | rs@cmail.carleton.ca  | fire     | COMP-360 |

  Scenario Outline: User attempts to register for a course without being logged in (Error Flow)
    Given the course <course> exist
    And the user is not logged in
    When user <user_name> requests register for course <course>
    Then the user will be notified that user is not logged in
      | course   |
      | ECSE-428 |
      | FACC-400 |
      | COMP-360 |

  Scenario Outline: User attempts to register for a course that doesn't exist (Error Flow)
    Given valid email <email> and password <password>
    And the course <course> doesn't exist
    And the user is logged in
    When user <user_name> requests register for course <course>
    Then the user will be notified that the course does not exist
      | user_name     | password | course   |
      | B_Weiss       | aslkda   | LAME-101 |
      | Ben_Weiss     | aasa     | BIRD-101 |
      | Ryan_Schuette | fire     | FREE-999 |
