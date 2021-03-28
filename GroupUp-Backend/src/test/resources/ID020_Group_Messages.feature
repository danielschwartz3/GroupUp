Feature: Send a Group Message

As a GroupUp Student
I want to send a group message
So that I can communicate with my peers

  Scenario Outline: create a group and send a message (Normal Flow)
    Given the following students exist:
      | username  | email               | name            | institution       | 
      | B_Weiss44 | ben@mail.mcgill.ca  | Ben Weiss       | McGill University | 
      | Ry_schu   | ryan@mail.mcgill.ca | Ryan Schue      | McGill University | 
      | dan_sch   | dan@mail.mcgill.ca  | Daniel Schwartz | McGill University | 
     And the student Daniel does not have any chats
     When the user Daniel tries to send the following message:
      | sender_email       | content       | date                | 
      | dan@mail.mcgill.ca | hello friends | 2021-03-03-12:08:00 | 
     Then a group chat will be created
     And the group chat will have the following messages:
      | sender_email       | content       | date                | 
      | dan@mail.mcgill.ca | hello friends | 2021-03-03-12:08:00 | 
  
  Scenario Outline: Send a message to an existing group (Alternate Flow)
    Given the following students exist:
      | username  | email               | name            | institution       | 
      | B_Weiss44 | ben@mail.mcgill.ca  | Ben Weiss       | McGill University | 
      | Ry_schu   | ryan@mail.mcgill.ca | Ryan Schue      | McGill University | 
      | dan_sch   | dan@mail.mcgill.ca  | Daniel Schwartz | McGill University | 
      And a group chat exists with those students
      And the following messages exist in the chat:
      | sender_email       | content               | date                | 
      | dan@mail.mcgill.ca | hey, what is up       | 2021-03-03-12:00:00 | 
      | ben@mail.mcgill.ca | I am good how are you | 2021-03-03-12:03:00 | 
      | dan@mail.mcgill.ca | adfasda               | 2021-03-03-12:05:00 | 
     When the user Ben tries to send the following message:
      | sender_email       | content       | date                | 
      | ben@mail.mcgill.ca | hello friends | 2021-03-03-12:08:00 | 
     Then the group chat will have the following messages:
      | sender_email       | content                       | date                | 
      | dan@mail.mcgill.ca | hey, what is up               | 2021-03-03-12:00:00 | 
      | ben@mail.mcgill.ca | I am good how are you         | 2021-03-03-12:03:00 | 
      | dan@mail.mcgill.ca | adfasda											 | 2021-03-03-12:05:00 | 
      | ben@mail.mcgill.ca | hello friends                 | 2021-03-03-12:08:00 | 
  
  
