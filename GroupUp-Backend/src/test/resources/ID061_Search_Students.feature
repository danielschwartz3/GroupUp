Feature: Search Student List

As a user
I would like to search for students by name
So that I can quickly find the person I want to message

  Scenario Outline: User searches for student that exists (Normal Flow)
  
    Given a user is logged in
    Given the following students exist:
      | username  | email                 | name           | institution       | 
      | B_Weiss22 | bw@mail.mcgill.ca     | Benjamin Weiss | McGill University | 
      | B_Weiss44 | bweiss@mail.mcgill.ca | Ben Weiss      | McGill University | 
      | Ry_schu   | rs@mail.mcgill.ca     | Ry Schue       | McGill University | 
  
     When the user searches for name "<name>"
     Then the user will see searched student
      
      Examples:
      | name           | 
      | Benjamin Weiss |
      | Ry Schue 			 |  
  
  Scenario Outline: User searches for student that does not exist (Error Flow)
    Given a user is logged in
    Given the following students exist:
      | username  | email                 | name           | institution       | 
      | B_Weiss22 | bw@mail.mcgill.ca     | Benjamin Weiss | McGill University | 
      | B_Weiss44 | bweiss@mail.mcgill.ca | Ben Weiss      | McGill University | 
      | Ry_schu   | rs@mail.mcgill.ca     | Ry Schue       | McGill University | 
  
     When the user searches for name "<name>"
     Then the user will receive the following message No student was found with this name
     
       Examples:
      | name           | 
      | Bob Builder		 |
      | Spongebob			 | 
  
