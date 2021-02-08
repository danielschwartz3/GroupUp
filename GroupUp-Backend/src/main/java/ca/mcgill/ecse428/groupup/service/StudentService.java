package ca.mcgill.ecse428.groupup.service;

import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse428.groupup.dao.StudentRepository;
import ca.mcgill.ecse428.groupup.model.Student;

public class StudentService {
	@Autowired
	StudentRepository studentRepository;
	
	Student getStudentByID(int id) {
		Student student = studentRepository.findById(id).orElse(null);
		if(student == null) throw new IllegalArgumentException("Student does not exist");
		return student;
	}
	
}
