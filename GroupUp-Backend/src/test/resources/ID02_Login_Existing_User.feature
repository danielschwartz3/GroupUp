Feature: Login existing user
  
  As a GroupUp user
  I would like to login to my account within the GroupUp sytem
  So that I can join group chats of the classes I am taking, interact with those chats, as well as take advantage of the other tools GroupUp offers
  Or perform admin responsibilities

  Scenario Outline: Student attempts login with an existing password/username combination (Normal Flow)
    Given valid email <email> and password <password>
    And a related student privileges
    When the user <email> requests access to the GroupUp system
    Then they will be granted access to the GroupUp system

    Examples: 
      | email                 | role          | password |
      | bw@mail.mcgill.ca     | Student       | aslkda   |
      | bweiss@mail.mcgill.ca | Administrator | aasa     |
      | rs@carleton.ca        | Student       | fire     |

  Scenario Outline: Admin attempts login with an existing password/username combination (Alternate Flow)
    Given valid email <email> and password <password>
    And a related admin privileges
    When the user <email> requests access to the GroupUp system
    Then they will be granted access to the GroupUp system

    Examples: 
      | email                 | role          | password |
      | bw@mail.mcgill.ca     | Student       | aslkda   |
      | bweiss@mail.mcgill.ca | Administrator | aasa     |
      | rs@carleton.ca        | Student       | fire     |

  Scenario Outline: User attempts login with an non-recognized username (Error Flow)
    Given a non-recognized email <email>
    When the user <email> requests access to the GroupUp system
    Then an error message is issued saying email not recognized

    Examples: 
      | email                         | role          | password |
      | bw@mail.invalid.mcgill.ca     | Student       | aslkda   |
      | bweiss@mail.invalid.mcgill.ca | Administrator | aasa     |
      | rs@carleton.invalid.ca        | Student       | fire     |

  Scenario Outline: User attempts login with an non-recognized password (Error Flow)
    Given a valid email <email>
    But an incorrect corresponding password <password>
    When the user <email> requests access to the GroupUp system
    Then an error message is issued saying password is incorrect

    Examples: 
      | email                 | role          | password |
      | bw@mail.mcgill.ca     | Student       | no       |
      | bweiss@mail.mcgill.ca | Administrator | not      |
      | rs@carleton.ca        | Student       | good     |
