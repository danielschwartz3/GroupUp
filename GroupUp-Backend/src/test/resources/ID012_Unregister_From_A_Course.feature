Feature: Unregister from a course
As a Student that is logged into the GroupUp system
I would like to unregister from a course that I was previously registered for.

  Background: 
    Given valid username <user_name> and password <password> 
      And the user is logged in
  
      | user_name     | password | 
      | B_Weiss       | aslkda   | 
      | Ben_Weiss     | aasa     | 
      | Ryan_Schuette | fire     | 
  
  Scenario Outline: User attemps to deregister from a pre-registered course (Normal Flow)
  
    Given the user is registered for this <course>
     When the user requests to deregister from this <course>
     Then they will no longer be enrolled
  
      | course   | 
      | ECSE-321 | 
      | COMP-250 | 
      | COMP-251 | 
  
  Scenario Outline: User attempts to deregister from a course that they are not registered for (Error Flow)
  
     When the user requests to de-register from the <course> they are not registered for
     Then a "User is not enrolled in this course" message is issued
  
      | course   | 
      | ECSE-222 | 
      | COMP-202 | 
      | ECSE-200 | 
  
  
