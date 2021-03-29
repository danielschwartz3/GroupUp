Feature: Logout existing user
  
  As a GroupUp user
  I would like to logout from my account
  So that I can protect allow other users to login

  Scenario Outline: Logged in student attempts logout (Normal Flow)
    Given student valid email <email> and password <password> is logged in
    When the user <email> requests logout of the GroupUp system
    Then the user will be logged out of the system

    Examples: 
      | email                 | role          | password |
      | bw@mail.mcgill.ca     | Student       | aslkda   |
      | bweiss@mail.mcgill.ca | Administrator | aasa     |
      | rs@carleton.ca        | Student       | fire     |

  Scenario Outline: Logged in administrator attempts logout (Alternate Flow)
    Given administrator valid email <email> and password <password> is logged in
    When the user <email> requests logout of the GroupUp system
    Then the user will be logged out of the system

    Examples: 
      | email                 | role          | password |
      | bw@mail.mcgill.ca     | Student       | aslkda   |
      | bweiss@mail.mcgill.ca | Administrator | aasa     |
      | rs@carleton.ca        | Student       | fire     |
