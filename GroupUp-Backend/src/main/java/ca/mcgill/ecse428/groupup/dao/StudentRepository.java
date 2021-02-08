package ca.mcgill.ecse428.groupup.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer>{
	List<Student> findByCourses_courseID(String courseID);
	Student findByAccount(Account account);
}
