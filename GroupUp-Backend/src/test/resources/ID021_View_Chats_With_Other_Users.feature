Feature: View chats with other users

As a GroupUp Student
I want to view my private messages with other students
So that I can read and review previous and current conversations I had with other students

  Scenario Outline: Studenta already has a thread of private messages with Studentb and requests to open it (Normal Flow)
    Given a user is logged in 
      And the user has a history of messages with studentb <Studentb>
     When the user opens his conversation with studentb <Studentb>
     Then the user will see a display of all the past messages
    Examples: 
      | Studentb       				|
      | kate@mail.mcgill.ca 	|
      | ward@mail.mcgill.ca 	|
  
  Scenario Outline: Studenta has no history of previous messaging with Studentb and requests to open the conversation(Alternate Flow)
    Given a user is logged in 
      And the user has no history of messages with studentb <Studentb>
     When the user opens his conversation with studentb <Studentb>
     Then the user will see a display of an empty messaging inbox
    Examples: 
      | Studentb       				|
      | kate@mail.mcgill.ca 	|
      | ward@mail.mcgill.ca 	| 
