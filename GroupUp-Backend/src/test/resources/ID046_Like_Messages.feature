Feature: Like a Message

As a GroupUp Student
I want to like a message
So that I can show that I have seen it or agree with it

  Scenario Outline: Like a message in a private chat (Normal Flow)
  		Given a student with name Daniel Schwartz and email is logged in 
      And a chat exists between him and a student Ben Weiss
      And the following messages exist in the chat:
      | sender_email       | content               | date                | 
      | dan@mail.mcgill.ca | hey, what is up       | 2021-03-03-12:00:00 | 
      | ben@mail.mcgill.ca | I am good how are you | 2021-03-03-12:03:00 | 
      | dan@mail.mcgill.ca | adfasda               | 2021-03-03-12:05:00 | 
      When the user Daniel tries to like a message
      Then the message will be liked by the user Daniel
  
  Scenario Outline: Like a message in a group chat (Alternate Flow)
  
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
     When the user Daniel tries to like a message
     Then the message will be liked by the user Daniel
  
  Scenario Outline: Like a message that someone else already liked in a private chat (Alternate Flow)
     	Given a student with name Daniel Schwartz and email is logged in 
      And a chat exists between him and a student Ben Weiss
      And the following messages exist in the chat:
      | sender_email       | content               | date                | 
      | dan@mail.mcgill.ca | hey, what is up       | 2021-03-03-12:00:00 | 
      | ben@mail.mcgill.ca | I am good how are you | 2021-03-03-12:03:00 | 
      | dan@mail.mcgill.ca | adfasda               | 2021-03-03-12:05:00 | 
      And the user Ben likes a message
      When the user Daniel tries to like the message
      Then the message will be liked by Daniel and Ben
  
  Scenario Outline: Like a message that someone else already liked in a group chat (Alternate Flow)
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
      And the user Ben likes a message
     	When the user Daniel tries to like the message
      Then the message will be liked by Daniel and Ben
  
  Scenario Outline: Like a message that I already liked in a private chat (Error Flow)
  		Given a student with name Daniel Schwartz and email is logged in 
      And a chat exists between him and a student Ben Weiss
      And the following messages exist in the chat:
      | sender_email       | content               | date                | 
      | dan@mail.mcgill.ca | hey, what is up       | 2021-03-03-12:00:00 | 
      | ben@mail.mcgill.ca | I am good how are you | 2021-03-03-12:03:00 | 
      | dan@mail.mcgill.ca | adfasda               | 2021-03-03-12:05:00 | 
      And the user Daniel likes a message
      When the user Daniel tries to like the message
      Then the message will be liked by the user Daniel
  
  Scenario Outline: Like a message that I already liked in a group chat (Error Flow)
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
      And the user Daniel likes a message
     	When the user Daniel tries to like the message
     	Then the message will be liked by the user Daniel
  
  
