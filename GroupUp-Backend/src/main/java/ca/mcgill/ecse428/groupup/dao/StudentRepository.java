package ca.mcgill.ecse428.groupup.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Course;
import ca.mcgill.ecse428.groupup.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer>{
	List<Student> findAllByCourses(Course course);
	Student findByAccount(Account account);
}
