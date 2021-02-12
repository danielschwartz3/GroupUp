package ca.mcgill.ecse428.groupup.dao;

import ca.mcgill.ecse428.groupup.model.Course;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, String> {
	List<Course> findByStudentsId(int id);
	List<Course> findByFaculty(String faculty);
	 
}
