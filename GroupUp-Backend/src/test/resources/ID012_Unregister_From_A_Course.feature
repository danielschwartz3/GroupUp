Feature: Unregister from a course
  As a Student that is logged into the GroupUp system
  I would like to unregister from a course that I was previously registered for.

  Background: 
    Given valid email <email> and password <password>
    And the user is logged in
      | user_name             | password |
      | bw@mail.mcgill.ca     | aslkda   |
      | bweiss@mail.mcgill.ca | aasa     |
      | rs@cmail.carleton.ca  | fire     |

  Scenario Outline: User attemps to deregister from a pre-registered course (Normal Flow)
    Given the user is registered for this <course>
    When the user requests to deregister from this <course>
    Then they will no longer be enrolled
    
    Examples:
      | course   |
      | ECSE-321 |
      | COMP-250 |
      | COMP-251 |

  Scenario Outline: User attempts to deregister from a course that they are not registered for (Error Flow)
    When the user requests to de-register from the <course> they are not registered for
    Then a message is issued saying that you are not enrolled in this course
    
    Examples:
      | course   |
      | ECSE-222 |
      | COMP-202 |
      | ECSE-200 |
