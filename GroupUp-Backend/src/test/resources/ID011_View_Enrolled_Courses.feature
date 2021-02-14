Feature: View enrolled courses
  
  As a Student
  I would like to view the courses I am currently enrolled in
  So that I can ensure I am enrolled in the correct courses.


  Scenario Outline: A user that is enrolled in courses attempts to view enrolled courses (Normal Flow)
    Given valid email <email> and password <password>
    And the user is enrolled in the following courses:
    When the user requests view enrolled courses
    Then the user will see currently enrolled courses
    Examples:
      | email                 | password | course   |
      | bw@mail.mcgill.ca     | aslkda   | ECSE-428 |
      | bweiss@mail.mcgill.ca | aasa     | FACC-400 |
      | rs@carleton.ca        | fire     | COMP-360 |

  Scenario Outline: A user that is not enrolled in any courses attempts to view enrolled courses (Error Flow)
    Given valid email <email> and password <password>
    And the user is not enrolled in any courses
    When the user requests view enrolled courses
    Then the system will notify the user that you are not enrolled in any course
    Examples:
      | email                 | password | course   |
      | bw@mail.mcgill.ca     | aslkda   | ECSE-428 |
      | bweiss@mail.mcgill.ca | aasa     | FACC-400 |
      | rs@carleton.ca        | fire     | COMP-360 |
