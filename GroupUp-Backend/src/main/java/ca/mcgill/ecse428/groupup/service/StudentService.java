package ca.mcgill.ecse428.groupup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.groupup.dao.StudentRepository;
import ca.mcgill.ecse428.groupup.dto.StudentDTO;
import ca.mcgill.ecse428.groupup.dao.AccountRepository;
import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Course;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.utility.DTOUtil;


@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Transactional
	public Student createStudent(Student student) {
		if(student == null) throw new IllegalArgumentException("Student is null");
		student = studentRepository.save(student);
		return student;
	}
	
	@Transactional
	public Student getStudentByID(int id) {
		Student student = studentRepository.findById(id).orElse(null);
		if(student == null) throw new IllegalArgumentException("Student does not exist");
		return student;
	}
	
	@Transactional
	public List<Student> getStudentsByCourse(Course course){
		if(course == null)throw new IllegalArgumentException("Course does not exist");
		return studentRepository.findAllByCourses(course);
	}
	
	@Transactional
	public List<Student> getStudentsByCourse(Student student, Course course){
		if(course == null)throw new IllegalArgumentException("Course does not exist");
		if(!course.getStudents().contains(student)) throw new IllegalArgumentException("Student is not registered in the course");
		return studentRepository.findAllByCourses(course);
	}
	
    @Transactional
    public Student getStudentByEmail(String email) throws IllegalArgumentException{
        Account acc = accountRepository.findById(email).orElse(null);
        if (acc == null) {
            throw new IllegalArgumentException("The Account with email: " + email + " does not exist.");
        }
        
        Student std;
        
        try {
        	std = (Student) acc.getUserRole();
        } catch(Exception e) {
        	throw new IllegalArgumentException("This email is not associated with a student account.");
        }
        
        return std;
    }
	
}
