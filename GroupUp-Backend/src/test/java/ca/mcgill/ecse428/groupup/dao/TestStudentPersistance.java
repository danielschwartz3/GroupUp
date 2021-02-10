package ca.mcgill.ecse428.groupup.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse428.groupup.model.Student;

@SpringBootTest
public class TestStudentPersistance {
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	public static Student createSampleStudent() {
		Student student = new Student();
		return student;
	}
}
