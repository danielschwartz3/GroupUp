package ca.mcgill.ecse428.groupup.service;

import ca.mcgill.ecse428.groupup.dao.CourseRepository;
import ca.mcgill.ecse428.groupup.model.Course;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.utility.Condition;
import ca.mcgill.ecse428.groupup.utility.Semester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
  @Autowired
  private CourseRepository courseRepository;

  @Transactional
  public Course createCourse(String courseID, String faculty, String semester, String year,
      String courseSection, String courseName) {
    String error = "";
    if (!Condition.isValid(courseID)) {
      error += "CourseID cannot be empty";
    }
    if (!Condition.isValid(faculty)) {
      error += "faculty cannot be empty";
    }
    if (!Condition.isValid(semester)) {
      error += "semester cannot be empty";
    }
    if (!Condition.isValid(year)) {
      error += "year cannot be empty";
    }
    if (!Condition.isValid(courseSection)) {
      error += "section number cannot be empty";
    }
    if (!Condition.isValid(courseName)) {
      error += "course name cannot be empty";
    }

    if (error.length() != 0) {
      throw new IllegalArgumentException(error);
    }
    List<Course> courses = courseRepository.findByCourseID(courseID);


    if (Condition.courseExists(courses, courseID, semester, courseSection, year)) {
      String errorMessage = "This course with courseID: " + courseID + " section: " + courseSection
          + " already exists for the " + semester + year + " semester ";
      throw new IllegalArgumentException(errorMessage);
    }
    Course course = new Course();
    course.setCourseID(courseID);
    course.setFaculty(faculty);
    course.setCourseSection(courseSection);
    course.setSemester(Semester.valueOf(semester.toUpperCase()));
    course.setYear(year);
    course.setCourseName(courseName);

    courseRepository.save(course);


    return course;

  }

  @Transactional
  public Course updateCourse(int id, Course course) {
    Course persitedCourse = courseRepository.findById(id).orElse(null);

    if (!Condition.isValid(persitedCourse)) {
      throw new IllegalArgumentException("Course with id: " + id + " does not exist");
    }
    String courseID = course.getCourseID();
    if (Condition.isValid(courseID)) {
      persitedCourse.setCourseID(courseID);
    }

    String newFaculty = course.getFaculty();
    if (Condition.isValid(newFaculty)) {
      persitedCourse.setFaculty(newFaculty);
    }
    String newSem = course.getSemester().toString();
    if (Condition.isValid(newSem)) {
      persitedCourse.setSemester(Semester.valueOf(newSem));
    }
    String newYear = course.getYear();
    if (Condition.isValid(newYear)) {
      persitedCourse.setYear(newYear);
    }
    String newCrsSection = course.getCourseSection();
    if (Condition.isValid(newCrsSection)) {
      persitedCourse.setCourseSection(newCrsSection);
    }
    String courseName = course.getCourseName();
    if (Condition.isValid((courseName))) {
      persitedCourse.setCourseName(courseName);
    }
    courseRepository.save(persitedCourse);

    return persitedCourse;
  }

  @Transactional
  public Course registerStudent(Student student, Course course) {
    if (student == null)
      throw new IllegalArgumentException("Student does not exist");
    if (course == null)
      throw new IllegalArgumentException("Course does not exist");
    if (course.getStudents().contains(student))
      throw new IllegalArgumentException("Student already in the course");
    course.addStudent(student);
    courseRepository.save(course);
    return course;
  }

  @Transactional
  public Course unregisterStudent(Student student, Course course) {
    if (student == null)
      throw new IllegalArgumentException("Student does not exist");
    if (course == null)
      throw new IllegalArgumentException("Course does not exist");
    if (!course.getStudents().contains(student))
      throw new IllegalArgumentException("Student is not registered in the course");
    course.removeStudent(student);
    courseRepository.save(course);
    return course;
  }

  @Transactional
  public Course getCourseByID(int id) {
    Course course = courseRepository.findById(id).orElse(null);
    if (course == null) {
      throw new IllegalArgumentException(
          "There is no course with id: " + id + " does not exist. Please add the course first");
    }
    return course;
  }

  @Transactional
  public List<Course> getCourseByCourseID(String id) { // made this quickly for testing
    List<Course> course = courseRepository.findByCourseID(id);
    return course;
  }

  @Transactional
  public List<Course> getCoursesByYearBySemester(String year, String semester) {
    semester = semester.toUpperCase();
    String error = "";
    if (!Condition.isValid(year)) {
      error += "CourseID cannot be empty";
    }
    if (!Condition.isValid(semester)) {
      error += "semester cannot be empty";
    }
    if (error.length() != 0) {
      throw new IllegalArgumentException(error);
    }
    List<Course> courses = courseRepository.findByYearAndSemester(year, Semester.valueOf(semester));

    return courses;
  }

  @Transactional
  public List<Course> getCoursesByYearBySemesterBySection(String year, String semester,
      String section) {
    semester = semester.toUpperCase();
    String error = "";
    if (!Condition.isValid(year)) {
      error += "CourseID cannot be empty";
    }
    if (!Condition.isValid(semester)) {
      error += "semester cannot be empty";
    }
    if (error.length() != 0) {
      throw new IllegalArgumentException(error);
    }
    List<Course> courses = courseRepository.findByYearAndSemesterAndCourseSection(year,
        Semester.valueOf(semester), section);

    return courses;
  }

  @Transactional
  public List<Course> getCoursesBySemester(String semester) {
    if (!Condition.isValid(semester)) {
      throw new IllegalArgumentException("semester cannot be empty");
    }
    Semester sem = Semester.valueOf(semester);
    return toList(courseRepository.findBySemester(sem));
  }

  @Transactional
  public List<Course> getCoursesByYear(String year) {
    if (!Condition.isValid(year)) {
      throw new IllegalArgumentException("semester cannot be empty");
    }
    return toList(courseRepository.findByYear(year));
  }

  @Transactional
  public List<Course> getAllCourses() {
    return toList(courseRepository.findAll());
  }

  @Transactional
  public boolean deleteCourse(int id) {
    courseRepository.deleteById(id);
    return courseRepository.existsById(id);
  }

  @Transactional
  public List<Course> getEnrolledCourses(int id) { // shouldn't id for students be the email???
    return toList(courseRepository.findByStudentsId(id));
  }

  private <T> List<T> toList(Iterable<T> iterable) {
    List<T> resultList = new ArrayList<T>();
    for (T t : iterable) {
      resultList.add(t);
    }
    return resultList;
  }
}
