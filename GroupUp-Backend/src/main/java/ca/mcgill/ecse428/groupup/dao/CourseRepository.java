package ca.mcgill.ecse428.groupup.dao;

import ca.mcgill.ecse428.groupup.model.Course;

import java.util.List;

import ca.mcgill.ecse428.groupup.utility.Semester;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CourseRepository extends CrudRepository<Course, Integer> {
  List<Course> findByStudentsId(int id);

  List<Course> findByFaculty(String faculty);

  List<Course> findBySemester(Semester semester);

  List<Course> findByYear(String year);

  List<Course> findByCourseID(String courseID);

  List<Course> findByYearAndSemester(String year, Semester semester);

  List<Course> findByYearAndSemesterAndCourseSection(String year, Semester semester,
      String section);
}
