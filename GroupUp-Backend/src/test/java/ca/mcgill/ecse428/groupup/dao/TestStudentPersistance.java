package ca.mcgill.ecse428.groupup.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Course;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.model.UserRole;
import ca.mcgill.ecse428.groupup.utility.Semester;

@SpringBootTest
public class TestStudentPersistance {
	
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CourseRepository courseRepository;
	
	private static final String USERNAME = "username";
	private static final String NAME = "Full Name";
	private static final String EMAIL = "test@mail.mcgill.ca";
	private static final String INSTITUTION = "McGill";
	private static final String PASSWORD = "Password123";
	private static final Student STUDENTROLE = new Student();
	private static final int ID = 123456;
	private static final String COURSEID = "ECSE-428";
	
	/**
	 * The tear down process for every test
	 */
	@AfterEach
	void clearDatabase() {
		//Clear the table
		accountRepository.deleteAll();
		studentRepository.deleteAll();
	}
	
	@Test
	@Transactional
	void testCreateStudent() {
		Account newAccount = createSampleAccount();
		newAccount = accountRepository.save(newAccount);
		assertNotNull(newAccount);
		Student newStudent = new Student();
		newStudent.setAccount(newAccount);
		newAccount.setUserRole(newStudent);
		Student savedStudent = studentRepository.save(newStudent);
		assertNotNull(savedStudent);
		checkStudentEqual(newStudent, savedStudent);
		
	}
	
	@Test
	@Transactional
	void testFindAllByCourses() {
		Course newCourse = createSampleCourse();
		Student newStudent = createSampleStudent();
		newCourse.addStudent(newStudent);
		newStudent.addCourse(newCourse);
		newStudent = studentRepository.save(newStudent);
		List<Student> studentList = studentRepository.findAllByCourses(newCourse);
		Student savedStudent = studentList.get(0);
		assertNotNull(savedStudent);
		checkStudentEqual(newStudent, savedStudent);
	}
	
	@Test
	@Transactional
	void testFindByAccount() {
		Student newStudent = createSampleStudent();
		studentRepository.save(newStudent);
		Student savedStudent = studentRepository.findByAccount(newStudent.account);
		assertNotNull(savedStudent);
		checkStudentEqual(newStudent, savedStudent);
	}
	
	@Test
	@Transactional
	void testDeleteStudent() {
		Student newStudent = createSampleStudent();
		Student savedStudent = studentRepository.save(newStudent);
		studentRepository.delete(savedStudent);
		accountRepository.delete(savedStudent.account);
		savedStudent = studentRepository.findByAccount(savedStudent.account);
		assertNull(savedStudent);
	}
	
	void checkStudentEqual(Student student1, Student student2) {
		assertEquals(student1.getAccount(), student2.getAccount());
		assertEquals(student1.getCourses(), student2.getCourses());
		assertEquals(student1.getId(), student2.getId());
	}
	
	Account createSampleAccount() {
		Account account = new Account();
		account.setEmail(EMAIL);
		account.setInstitution(INSTITUTION);
		account.setName(NAME);
		account.setPassword(PASSWORD);
		account.setUserName(USERNAME);
//		account.setUserRole(STUDENTROLE);
		return account;
	}
	
	Student createSampleStudent() {
		Account account = new Account();
		account.setEmail(EMAIL);
		account.setInstitution(INSTITUTION);
		account.setName(NAME);
		account.setPassword(PASSWORD);
		account.setUserName(USERNAME);
		Student student = new Student();
		account.setUserRole(student);
		student.setAccount(account);
		student = studentRepository.save(student);
		assertNotNull(account);
		return student;
	}
	
	
	Course createSampleCourse() {
		Course course = new Course();
		course.setCourseID(COURSEID);
		course.setCourseName("class");
		course.setCourseSection("001");
		course.setFaculty("Engineering");
		course.setSemester(Semester.FALL);
		course.setYear("2021");
		course = courseRepository.save(course);
		return course;
	}
}
