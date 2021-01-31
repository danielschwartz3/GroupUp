Feature: View available courses

As a Student
I would like to view the courses that are available
So that I can register for the courses that I am taking. 

    Background:
        Given valid username <user_name> and password <password> 
        And the user is logged in

            | user_name     | password |
            | B_Weiss       | aslkda   |
            | Ben_Weiss     | aasa     |
            | Ryan_Schuette | fire     |

    Scenario Outline: A user attempts to view all courses in the system (Normal Flow)
        Given the following courses exist:
            
            | course   | semester | year |
            | ECSE-428 | winter   | 2021 |
            | FACC-400 | winter   | 2021 |
            | COMP-360 | winter   | 2021 |
            | COMP-360 | winter   | 2020 |
            | MECH-360 | summer   | 2021 |
            | FACC-300 | fall     | 2020 |


        When the user requests view available courses in "all" semester(s) in "all" year
        Then the user will see:
            | course   | semester | year |
            | ECSE-428 | winter   | 2021 |
            | FACC-400 | winter   | 2021 |
            | COMP-360 | winter   | 2021 |
            | COMP-360 | winter   | 2020 |
            | MECH-360 | summer   | 2021 |
            | FACC-300 | fall     | 2020 |

    Scenario Outline: A user attempts to view all courses in the system for a particulate semester and year (Alternate Flow)
        Given the following courses exist:
            
            | course   | semester | year |
            | ECSE-428 | winter   | 2021 |
            | FACC-400 | winter   | 2021 |
            | COMP-360 | winter   | 2021 |
            | COMP-360 | winter   | 2020 |
            | MECH-360 | summer   | 2021 |
            | FACC-300 | fall     | 2020 |


        When the user requests view available courses in "winter" semester(s) in "2021" year
        Then the user will see:
            | course   | semester | year |
            | ECSE-428 | winter   | 2021 |
            | FACC-400 | winter   | 2021 |
            | COMP-360 | winter   | 2021 |