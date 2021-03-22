Feature: Query Student List

As a user
I would query the student list
So that I can keep view people that I want to message

  Scenario Outline: User queries student list (Normal Flow)
  
    Given a user is logged in
    Given the following students exist:
      | username  | email                 | name           | institution       | 
      | B_Weiss22 | bw@mail.mcgill.ca     | Benjamin Weiss | McGill University | 
      | B_Weiss44 | bweiss@mail.mcgill.ca | Ben Weiss      | McGill University | 
      | Ry_schu   | rs@mail.mcgill.ca     | Ry Schue       | McGill University | 
  
     When the user queries the student list
     Then the student will see the list of students