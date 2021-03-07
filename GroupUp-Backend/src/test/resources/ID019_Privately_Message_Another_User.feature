Feature: Privately message another user

As a GroupUp Student
I would like to message other students in my classes privately
So that I can discuss private course topics without disturbing the rest of the class

  Scenario Outline: Student begins to private messages another student in a course (Normal Flow)
    Given a user is logged in 
      And the user is registered for this <course>
      And studentb <Studentb> is registered in the same course <course>
      And the user does not have an existing conversation with studentb <Studentb>
     When the user tries to message studentb <Studentb>
     Then studentb <Studentb> should receive a new message
    Examples: 
      | Studentb       			| course   | 
      | matt@mail.mcgill.ca | FACC-400 | 
      | ryan@mail.mcgill.ca | ECSE-428 | 
  
  Scenario Outline: Student a private messages to another student they have previously had a chat with (Alternate Flow)
    Given a user is logged in 
      And the user has an existing conversation with studentb <Studentb>
     When the user tries to message studentb <Studentb>
     Then studentb <Studentb> should receive a new message
    Examples: 
      | Studentb       			| 
      | matt@mail.mcgill.ca | 
      | ryan@mail.mcgill.ca | 
  
  