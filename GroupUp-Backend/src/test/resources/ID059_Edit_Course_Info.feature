Feature: Edit an existing course

  As an Administrator
  I would like to edit an existing course's information
  So that information is up to date for students

  Scenario Outline: A valid administrator attemps to edit a course (Normal Flow)
    Given valid administrator's username "<username>" name "<name>" email "<email>" institution "<institution>" and password "<password>"
    And the administrator is logged in
    And a course exists with course ID "<old_course_ID>", name "<old_course_name>", semester "<old_course_semester>", year "<old_course_year>", section "<old_course_section>", faculty "<old_faculty>"
    When the administrator attempts to changes the information for course "<old_course_ID>" to name "<new_course_name>", semester "<new_course_semester>", year "<new_course_year>", section "<new_course_section>", faculty "<new_faculty>"
    Then the course with with course ID "<old_course_ID>", has name "<new_course_name>", semester "<new_course_semester>", year "<new_course_year>", section "<new_course_section>", faculty "<new_faculty>"

    Examples:
      | email                 | password | old_course_ID | old_course_name            | old_course_semester | old_course_year | old_course_section | old_faculty |  new_course_name                    | new_course_semester | new_course_year | new_course_section | new_faculty | username| name     | institution |
      | bw@mail.mcgill.ca     | aslkda   | ECSE-428       | Software Eng Practice      | FALL                | 2020            | 001                | Engineering |  Software Engingeering Practice     | FALL                | 2020            | 002                | Engineering | atlas   | Fabrice  | McGill      |
      | bw@mail.mcgill.ca     | aslkda   | ECSE-428       | Software Eng Practice      | FALL                | 2020            | 001                | Engineering |  Software Engingeering Practice     | WINTER              | 2021            | 001                | Engineering | vigo    | Labeau   | Concordia   |
      | bw@mail.mcgill.ca     | aslkda   | ECSE-428       | Software Eng Practice      | FALL                | 2020            | 001                | Engineering |  Software Eng. Practice             | SUMMER              | 2020            | 003                | Engineering | davis   | Suzanne  | UBC         |
      | bweiss@mail.mcgill.ca | aasa     | FACC-400       | Eng. Professional Practice | WINTER              | 2021            | 002                | Engineering |  Enginttering Professional Practice | Summer              | 2021            | 002                | Engineering | Nani    | Fortier  | Havard      |