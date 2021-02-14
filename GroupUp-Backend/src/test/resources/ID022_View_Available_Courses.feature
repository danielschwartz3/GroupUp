Feature: View available courses
  
  As a Student
  I would like to view the courses that are available
  So that I can register for the courses that I am taking.

  Background: 
    Given a user is logged in

  Scenario Outline: A user attempts to view all courses in the system (Normal Flow)
    Given the following courses exist:
      | course   | semester | year |
      | ECSE-428 | WINTER   | 2021 |
      | FACC-400 | WINTER   | 2021 |
      | COMP-360 | WINTER   | 2021 |
      | COMP-360 | WINTER   | 2020 |
      | MECH-360 | SUMMER   | 2021 |
      | FACC-300 | FALL     | 2020 |
    When the user requests view available courses in all semesters in every year
    Then the user will see all the courses

  Scenario Outline: A user attempts to view all courses in the system for a particular semester and year (Alternate Flow)
    Given the following courses exist:
      | course   | semester | year |
      | ECSE-428 | WINTER   | 2021 |
      | FACC-400 | WINTER   | 2021 |
      | COMP-360 | WINTER   | 2021 |
      | COMP-360 | WINTER   | 2020 |
      | MECH-360 | SUMMER   | 2021 |
      | FACC-300 | FALL     | 2020 |
    When the user requests view available courses in <semester> semester in <year> year
    Then the user will see all the courses for that semester
    
    Examples:
      | semester | year |
      | WINTER   | 2021 |
      | WINTER   | 2020 |
      | FALL 		 | 2020 |
