Feature: View enrolled courses

As a Student
I would like to view the courses I am currently enrolled in
So that I can ensure I am enrolled in the correct courses. 

    Background:
        Given valid username <user_name> and password <password> 
        And the user is logged in

            | user_name     | password |
            | B_Weiss       | aslkda   |
            | Ben_Weiss     | aasa     |
            | Ryan_Schuette | fire     |

    Scenario Outline: A user that is enrolled in courses attempts to view enrolled courses (Normal Flow)
        Given the user is enrolled in the following courses:
            
            | course   |
            | ECSE-428 |
            | FACC-400 |
            | COMP-360 |

        When the user requests view enrolled courses
        Then the user will see currently enrolled courses

    Scenario Outline: A user that is not enrolled in any courses attempts to view enrolled courses (Error Flow)    
        Given the user is not enrolled in any courses
        When the user requests view enrolled courses
        Then the system will notify the user "You are not enrolled in any course"