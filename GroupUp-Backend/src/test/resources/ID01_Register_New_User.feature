Feature: Register new user

As a student
I would like to become a user of the GroupUp System
So that I can join groups related to the courses I am registered in

Scenario Outline: New student attempts to become a user (Normal Flow)

Given student <user_name> with student email<user_email> and institution name <user_institution> is student in good standing
When student <user_name> requests user access to the GroupUp System
Then a new <user_name> and initial <password> are generated

      | user_name    | user_email           | role    | student_name | user_institution   | password | 
      | B_Weiss      | bw@mail.mcgill.ca    | student | B Weiss      | McGill University  | aslkda   | 
      | Ben_Weiss    | bweiss@mail.mcgill.ca| student | Ben Weiss    | McGill University  | aasa     | 
      | Ryan_Schuette| rs@cmail.carleton.ca | student |	Ryan Schuette| Carleton University| fire     | 

Scenario Outline: Non-student attempts to become a user (Error Flow)

Given John Doe uses email INVALID_EMAIL to request to be a registered user 
When John Doe requests user access to the GroupUp System
Then an "Unverified student request" message is issued


Scenario Outline: Existing user attempts to become a user (Error Flow)

Given James Smith is a user of the GoupUp System
When James Smith requests user access to the GroupUp System
Then an "Already registered" message is issued


