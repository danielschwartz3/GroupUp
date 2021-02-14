Feature: Add a new course
  
  As a Student
  I would like to add a new course
  So that I can join the GroupUp chat for the course

  Scenario Outline: A valid user attemps to add a new course (Normal Flow)
    Given valid email <email> and password <password>
    And the user is logged in
    When the user requests to add a new course <new_course>
    Then the course <new_course> is added to the system
    And the user is registered to the course

    Examples: 
      | email                 | password | new_course |
      | bw@mail.mcgill.ca     | aslkda   | ECSE-428   |
      | bweiss@mail.mcgill.ca | aasa     | FACC-400   |
      | rs@carleton.ca        | fire     | COMP-360   |

  Scenario Outline: User attempts to add a course that already exists (Alternative Flow)
    Given valid email <email> and password <password>
    And the course <new_course> already exist in the system
    When the user requests to add a new course <new_course>
    Then a message is issued to the user saying course already exists
    And the user is registered to the course

    Examples: 
      | email                 | password | new_course |
      | bw@mail.mcgill.ca     | aslkda   | ECSE-428   |
      | bweiss@mail.mcgill.ca | aasa     | FACC-400   |
      | rs@carleton.ca        | fire     | COMP-360   |

  Scenario Outline: User attempts to add an invalid new course name (Error Flow)
    Given valid email <email> and password <password>
    And the course <new_course> is invalid format
    When the user requests to add a new course <new_course>
    Then an error message saying course name is invalid format is issued

    Examples: 
      | email                 | password | new_course |
      | bw@mail.mcgill.ca     | aslkda   |  invalid		|
      | bweiss@mail.mcgill.ca | aasa     |  invalid	  |
      | rs@carleton.ca        | fire     |	invalid   |
