Feature: Login existing user

As a GroupUp user
I would like to login to my account within the GroupUp sytem
So that I can join group chats of the classes I am taking, interact with those chats, as well as take advantage of the other tools GroupUp offers
Or perform admin responsibilities

  Scenario: Student attempts login with an existing password/username combination (Normal Flow)
  
    Given valid username <user_name> and password <password> 
      And a related student privileges <role>
     When user <user_name> requests access to the GroupUp system
     Then they will be granted access to the GroupUp system as a student
  
      | user_name     | password | role    | 
      | B_Weiss       | aslkda   | student | 
      | Ben_Weiss     | aasa     | student | 
      | Ryan_Schuette | fire     | student | 
  
  Scenario: Admin attempts login with an existing password/username combination (Alternate Flow)
  
    Given valid username <user_name> and password <password> 
      And a related admin privileges <role>
     When user <user_name> requests access to the GroupUp system
     Then they will be granted access to the GroupUp system
  
      | user_name       | password | role  | 
      | B_Weiss_a       | aslkda   | admin | 
      | Ben_Weiss_a     | aasa     | admin | 
      | Ryan_Schuette_a | fire     | admin | 
  
  Scenario: User attempts login with an non-recognized username (Error Flow)
  
    Given a non-recognized username <user_name>
     When the user requests access to the GroupUp system
     Then an "Username not recognized" message is issued
  
      | user_name        | password  | 
      | B_Weiss_NO       | aslkda123 | 
      | Ben_Weiss_NO     | aasa123   | 
      | Ryan_Schuette_NO | fire123   | 
  
  Scenario: User attempts login with an non-recognized password (Error Flow)
  
    Given a valid username <user_name> 
      But an incorrect corresponding password 
     When the user requests access to the GroupUp system
     Then an "Incorrect Password" message is issued
      And a record of the attempt is sent to the System Administrator
  
      | user_name     | 
      | B_Weiss       | 
      | Ben_Weiss     | 
      | Ryan_Schuette | 
  
  
