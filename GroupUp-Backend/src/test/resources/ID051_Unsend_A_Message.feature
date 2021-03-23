Feature: Unsend a Message

As a GroupUp Student
I want to unsend a message
So that I can remove typos or accidental messages

  Scenario Outline: Unsend my own message in a private chat (Normal Flow)
    Given a student with name Daniel Schwartz and email is logged in 
      And a chat exists between him and a student Ben Weiss
      And the following messages exist in the private chat:
      | sender_email       | content               | date                | 
      | dan@mail.mcgill.ca | hey, what is up       | 2021-03-03-12:00:00 | 
      | ben@mail.mcgill.ca | I am good how are you | 2021-03-03-12:03:00 | 
      | dan@mail.mcgill.ca | adfasda               | 2021-03-03-12:05:00 | 
     When the user Daniel tries to unsend the following message:
      | sender_email       | content | date                | 
      | dan@mail.mcgill.ca | adfasda | 2021-03-03-12:05:00 | 
     Then the normal flow chat will have the following messages:
      | sender_email       | content                       | date                | 
      | dan@mail.mcgill.ca | hey, what is up               | 2021-03-03-12:00:00 | 
      | ben@mail.mcgill.ca | I am good how are you         | 2021-03-03-12:03:00 | 
      | dan@mail.mcgill.ca | This message has been unsent. | 2021-03-03-12:05:00 | 
  
  #Scenario Outline: Unsend my own message in a group chat (Alternate Flow)
    #Given the following students exist:
      #| username  | email               | name            | institution       | 
      #| B_Weiss44 | ben@mail.mcgill.ca  | Ben Weiss       | McGill University | 
      #| Ry_schu   | ryan@mail.mcgill.ca | Ryan Schue      | McGill University | 
      #| dan_sch   | dan@mail.mcgill.ca  | Daniel Schwartz | McGill University | 
      #And a chat exists with the following students:
      #| username  | email               | name            | institution       | 
      #| B_Weiss44 | ben@mail.mcgill.ca  | Ben Weiss       | McGill University | 
      #| Ry_schu   | ryan@mail.mcgill.ca | Ryan Schue      | McGill University | 
      #| dan_sch   | dan@mail.mcgill.ca  | Daniel Schwartz | McGill University | 
      #And the following messages exist in the chat:
      #| sender_email       | content               | date                | 
      #| dan@mail.mcgill.ca | hey, what is up       | 2021-03-03-12:00:00 | 
      #| ben@mail.mcgill.ca | I am good how are you | 2021-03-03-12:03:00 | 
      #| dan@mail.mcgill.ca | adfasda               | 2021-03-03-12:05:00 | 
     #When the user with email dan@mail.mcgill.ca tries to unsend the following message:
      #| sender_email       | content | date                | 
      #| dan@mail.mcgill.ca | adfasda | 2021-03-03-12:05:00 | 
     #Then chat will have the following messages:
      #| sender_email       | content                       | date                | 
      #| dan@mail.mcgill.ca | hey, what is up               | 2021-03-03-12:00:00 | 
      #| ben@mail.mcgill.ca | I am good how are you         | 2021-03-03-12:03:00 | 
      #| dan@mail.mcgill.ca | This message has been unsent. | 2021-03-03-12:05:00 | 
  #
  #Scenario Outline: Unsend someone elses message in a private chat (Error Flow)
    #Given a student with name Daniel Schwartz and email dan@mail.mcgill.ca is logged in 
      #And a student with name Ben Weiss and email ben@mail.mcgill.ca
      #And a chat exists with students dan@mail.mcgill.ca and ben@mail.mcgill.ca
      #And the following messages exist in the chat:
      #| sender_email       | content               | date                | 
      #| dan@mail.mcgill.ca | hey, what is up       | 2021-03-03-12:00:00 | 
      #| ben@mail.mcgill.ca | I am good how are you | 2021-03-03-12:03:00 | 
      #| dan@mail.mcgill.ca | adfasda               | 2021-03-03-12:05:00 | 
     #When the user with email ben@mail.mcgill.ca tries to unsend the following message:
      #| sender_email       | content | date                | 
      #| dan@mail.mcgill.ca | adfasda | 2021-03-03-12:05:00 | 
     #Then chat will have the following messages:
      #| sender_email       | content               | date                | 
      #| dan@mail.mcgill.ca | hey, what is up       | 2021-03-03-12:00:00 | 
      #| ben@mail.mcgill.ca | I am good how are you | 2021-03-03-12:03:00 | 
      #| dan@mail.mcgill.ca | adfasda               | 2021-03-03-12:05:00 | 
      #And an error message saying You do not have permission to unsend this message will be thrown
  #
  #Scenario Outline: Unsend someone elses message in a group chat (Error Flow)
    #Given the following students exist:
      #| username  | email               | name            | institution       | 
      #| B_Weiss44 | ben@mail.mcgill.ca  | Ben Weiss       | McGill University | 
      #| Ry_schu   | ryan@mail.mcgill.ca | Ryan Schue      | McGill University | 
      #| dan_sch   | dan@mail.mcgill.ca  | Daniel Schwartz | McGill University | 
      #And a chat exists with the following students:
      #| username  | email               | name            | institution       | 
      #| B_Weiss44 | ben@mail.mcgill.ca  | Ben Weiss       | McGill University | 
      #| Ry_schu   | ryan@mail.mcgill.ca | Ryan Schue      | McGill University | 
      #| dan_sch   | dan@mail.mcgill.ca  | Daniel Schwartz | McGill University | 
      #And the following messages exist in the chat:
      #| sender_email       | content               | date                | 
      #| dan@mail.mcgill.ca | hey, what is up       | 2021-03-03-12:00:00 | 
      #| ben@mail.mcgill.ca | I am good how are you | 2021-03-03-12:03:00 | 
      #| dan@mail.mcgill.ca | adfasda               | 2021-03-03-12:05:00 | 
     #When the user with email ben@mail.mcgill.ca tries to unsend the following message:
      #| sender_email       | content | date                | 
      #| dan@mail.mcgill.ca | adfasda | 2021-03-03-12:05:00 | 
     #Then chat will have the following messages:
      #| sender_email       | content               | date                | 
      #| dan@mail.mcgill.ca | hey, what is up       | 2021-03-03-12:00:00 | 
      #| ben@mail.mcgill.ca | I am good how are you | 2021-03-03-12:03:00 | 
      #| dan@mail.mcgill.ca | adfasda               | 2021-03-03-12:05:00 | 
      #And an error message saying You do not have permission to unsend this message will be thrown
  #
  #
