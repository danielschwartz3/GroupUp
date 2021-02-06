package ca.mcgill.ecse428.groupup.dao;

import ca.mcgill.ecse428.groupup.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, String> {

    boolean existsByCourseID(String courseID);

    Course findCourseByCourseID(String courseID);
}
