Feature: Register new user

  As a student
  I would like to become a user of the GroupUp System
  So that I can join groups related to the courses I am registered in

  Scenario Outline: New student attempts to become a user (Normal Flow)

    Given student <user_name> with student email <user_email> and institution name "<user_institution>" is student in good standing
    When student <user_name> requests user access to the GroupUp System
    Then a new <user_name> and initial <password> are generated
    Examples:
      | user_name     | user_email            | user_institution    | password |
      | B_Weiss       | bw@mail.mcgill.ca     | McGill University   | aslkda   |
      | Ben_Weiss     | bweiss@mail.mcgill.ca | McGill University   | aasa     |
      | Ryan_Schuette | rs@mail.mcgill.ca  		| McGill University 	| fire     |

  Scenario: Non-student attempts to become a user (Error Flow)

    Given John Doe uses email INVALID_EMAIL to request to be a registered user
    When John Doe requests user access to the GroupUp System
    Then John Doe will be notified of an invalid email


  Scenario: Existing user attempts to become a user (Error Flow)

    Given James Smith is a user of the GoupUp System
    When James Smith requests user access to the GroupUp System
    Then James Smith will be notified that he is already registered


