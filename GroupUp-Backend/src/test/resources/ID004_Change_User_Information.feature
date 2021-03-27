
Feature: Edit user information

As a user
I would like to edit my information
So that I can keep all my information up to date

  Scenario Outline: User changes information (Normal Flow)
  
    Given user with username "<old_username>", email "<old_email>", name "<old_name>", institution "<old_institution>", password "<old_password>"
     When the user with email "<old_email>" tries to change their information to username "<new_username>", email "<new_email>", name "<new_name>", institution "<new_institution>", password "<new_password>"
     Then the user with email "<new_email>" will have information username "<new_username>", email "<new_email>", name "<new_name>", institution "<new_institution>", password "<new_password>"
  
    Examples: 
      | old_username  | old_email             | old_name      | old_institution   | old_password | new_username | new_email         | new_name       | new_institution   | new_password | 
      | B_Weiss       | bw@mail.mcgill.ca     | Ben Weiss     | McGill University | aslkda       | B_Weiss22    | bw@mail.mcgill.ca | Benjamin Weiss | McGill University | afnadfanld   | 
      | Ben_Weiss     | bweiss@mail.mcgill.ca | Ben Weiss     | McGill University | aasa         | B_Weiss44    | bweiss@mail.mcgill.ca | Ben Weiss  | McGill University | bliknlhbk    | 
      | Ryan_Schuette | rs@mail.mcgill.ca     | Ryan Schuette | McGill University | fire         | Ry_schu      | rs@mail.mcgill.ca | Ry Schue       | McGill University | afdfafad     | 
  
  Scenario Outline: USer changes email (Error Flow)
  
    Given user's username "<old_username>", email "<old_email>", name "<old_name>", institution "<old_institution>", password "<old_password>"
     When the user tries to change their information to username "<new_username>", email "<new_email>", name "<new_name>", institution "<new_institution>", password "<new_password>"
     Then the user will receive a message, You cannot change your email, please enter valid information"
     Then the user will have information username "<old_username>", email "<old_email>", name "<old_name>", institution "<old_institution>", password "<old_password>"
  
    Examples: 
      | old_username  | old_email             | old_name      | old_institution   | old_password | new_username | new_email            | new_name       | new_institution   | new_password | 
      | B_Weiss       | bw@mail.mcgill.ca     | Ben Weiss     | McGill University | aslkda       | B_Weiss22    | bw12@mail.mcgill.ca  | Benjamin Weiss | McGill University | afnadfanld   | 
      | Ben_Weiss     | bweiss@mail.mcgill.ca | Ben Weiss     | McGill University | aasa         | B_Weiss44    | bw112@mail.mcgill.ca | Ben Weiss      | McGill University | bliknlhbk    | 
      | Ryan_Schuette | rs@mail.mcgill.ca     | Ryan Schuette | McGill University | fire         | Ry_schu      | rs12@mail.mcgill.ca  | Ry Schue       | McGill University | afdfafad     | 
  
  Scenario Outline: USer changes institution (Error Flow)
  
    Given user with the username "<old_username>", email "<old_email>", name "<old_name>", institution "<old_institution>", password "<old_password>"
     When the user tries to change their username "<new_username>", email "<new_email>", name "<new_name>", institution "<new_institution>", password "<new_password>"
     Then the user will receive a message, You cannot change your institution as it is linked to your email, please enter valid information"
     Then the user will have username "<old_username>", email "<old_email>", name "<old_name>", institution "<old_institution>", password "<old_password>"
  
    Examples: 
      | old_username  | old_email             | old_name      | old_institution   | old_password | new_username | new_email         | new_name       | new_institution     | new_password | 
      | B_Weiss       | bw@mail.mcgill.ca     | Ben Weiss     | McGill University | aslkda       | B_Weiss22    | bw@mail.mcgill.ca | Benjamin Weiss | Montreal University | afnadfanld   | 
      | Ben_Weiss     | bweiss@mail.mcgill.ca | Ben Weiss     | McGill University | aasa         | B_Weiss44    | bweiss@mail.mcgill.ca | Ben Weiss  | Montreal University | bliknlhbk    | 
      | Ryan_Schuette | rs@mail.mcgill.ca     | Ryan Schuette | McGill University | fire         | Ry_schu      | rs@mail.mcgill.ca | Ry Schue       | Montreal University | afdfafad     | 
  
  
