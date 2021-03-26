package ca.mcgill.ecse428.groupup.utility;

import ca.mcgill.ecse428.groupup.model.Course;

import java.util.List;

public class Condition {
  public static boolean isValid(String s) {
    return s != null && s.trim().length() != 0;
  }

  public static boolean isValid(Object o) {
    return o != null;
  }

  public static boolean courseExists(List<Course> courses, String courseID, String semester,
      String courseSection, String year) {
    for (Course c : courses) {
      if (c.getCourseID().equals(courseID) && c.getSemester().equals(Semester.valueOf(semester))
          && c.getCourseSection().equals(courseSection) && c.getYear().equals(year)) {
        return true;
      }
    }
    return false;
  }

}
