Feature: Add a new course

As a Student
I would like to add a new course
So that I can join the GroupUp chat for the course

  Background: 
    Given valid username <user_name> and password <password> 
      And the user is logged in
  
      | user_name     | password | 
      | B_Weiss       | aslkda   | 
      | Ben_Weiss     | aasa     | 
      | Ryan_Schuette | fire     | 
  
  Scenario Outline: A valid user attemps to add a new course (Normal Flow)
  
     When the user requests to add a new course <new_course>
      And the course <new_course> does not exist
      And the course <new_course> is valid
     Then the course <new_course> is added to the system
      And the user is registered to the course
  
      | new_course | 
      | ECSE-428   | 
      | FACC-400   | 
      | COMP-360   | 
  
  Scenario Outline: User attempts to add a course that already exists (Alternative Flow)
  
     When the user requests to add a new course <new_course>
      And the course <new_course> already exist in the system
     Then a "course already exist" message is issued to the user
      And the system should register the user to the course
      And add the user to GroupUp chat of the course
  
      | new_course | 
      | ECSE-428   | 
      | FACC-400   | 
      | COMP-360   | 
  
  Scenario Outline: User attempts to add an invalid new course name (Error Flow)
  
     When the user requests to add a new course <new_course>
      And the course <new_course> does not exist
      And the course <new_course> is invalid format
     Then an error message "course name is invalid format" is issued to the user
  
      | new_course | 
      | ECSE 428   | 
      | 400FACC    | 
      | COMP360    | 
      | COM-500    | 
  
  
