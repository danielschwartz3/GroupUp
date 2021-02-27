package ca.mcgill.ecse428.groupup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse428.groupup.dao.StudentRepository;
import ca.mcgill.ecse428.groupup.model.Course;
import ca.mcgill.ecse428.groupup.model.Student;


@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	
	public Student getStudentByID(int id) {
		Student student = studentRepository.findById(id).orElse(null);
		if(student == null) throw new IllegalArgumentException("Student does not exist");
		return student;
	}
	
	public List<Student> getStudentsByCourse(Course course){
		if(course == null)throw new IllegalArgumentException("Course does not exist");
		return studentRepository.findAllByCourses(course);
	}
	
}
