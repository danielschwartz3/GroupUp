Feature: Add a new course

As a Student
I would like to add a new course
So that I can join the GroupUp chat for the course

  Background:
    Given valid email <email> and password <password> 
      And the user is logged in
      | user_name             | password |
      | bw@mail.mcgill.ca     | aslkda   |
      | bweiss@mail.mcgill.ca | aasa     |
      | rs@cmail.carleton.ca  | fire     |
  Scenario Outline: A valid user attemps to add a new course (Normal Flow)
  
     When the user requests to add a new course <new_course>
     Then the course <new_course> is added to the system
      And the user is registered to the course
      Examples:
      | new_course | 
      | ECSE-428   | 
      | FACC-400   | 
      | COMP-360   | 
  
  Scenario Outline: User attempts to add a course that already exists (Alternative Flow)
  	Given the course <new_course> already exist in the system
     When the user requests to add a new course <new_course>
     Then a "course already exist" message is issued to the user
      And the system should register the user to the course
      And add the user to GroupUp chat of the course
      Examples:
      | new_course | 
      | ECSE-428   | 
      | FACC-400   | 
      | COMP-360   | 
  
  Scenario Outline: User attempts to add an invalid new course name (Error Flow)
  	Given the course <new_course> is invalid format
     When the user requests to add a new course <new_course>
     Then an error message "course name is invalid format" is issued to the user
      Examples:
      | new_course | 
      | ECSE 428   | 
      | 400FACC    | 
      | COMP360    | 
      | COM-500    | 
  
  
