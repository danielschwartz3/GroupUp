Feature: View my course classmates list

As a Student
I would like to view the list of classmates in my course
So that I can later communicate and form groups with them.

  Background: 
    Given a user is logged in
  
  Scenario Outline: A user attempts to view all classmates in one of their enrolled courses (Normal Flow)
    Given the user is registered for this <course>
     When the user requests view the course classmates list
     Then the user will see the course classmates list
  
    Examples: 
      | course   | 
      | ECSE-428 | 
      | ECSE-360 | 
      | COMP-360 | 
  
  Scenario Outline: A user attempts to view all classmates in a course they are not enrolled in (Error Flow)
    Given the user is not enrolled in course <course>
     When the user requests view the course classmates list
     Then the user will be notified that you are not enrolled in this course
  
    Examples: 
      | course   | 
      | ECSE-428 | 
      | ECSE-360 | 
      | COMP-360 | 
  