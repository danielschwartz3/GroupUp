package ca.mcgill.ecse428.groupup.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
import ca.mcgill.ecse428.groupup.utility.Semester;

@SpringBootTest
public class TestCoursePersistance {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AccountRepository accountRepository;
    private static final String FACULTY = "Engineering";
    private static final String COURSE_ID = "ECSE-428";
    private static final String YEAR = "2021";
    private static final String SECTION = "001";

    private static final Semester SEMESTER = Semester.WINTER;

    /**
     * The tear down process for every test
     */
    @AfterEach
    void clearDatabase() {
        // Clear the table to avoid inconsistency
        courseRepository.deleteAll();
        accountRepository.deleteAll();
        studentRepository.deleteAll();
    }

    @Test
    @Transactional
    void testCreateCourse() {
        Course newCourse = createSampleCourse();
        Course savedCourse = courseRepository.save(newCourse);
        assertNotNull(savedCourse);
        checkCourseEqual(newCourse, savedCourse);
    }

    @Test
    @Transactional
    void testFindCourseByID() {
        Course newCourse = createSampleCourse();
        int id = courseRepository.save(newCourse).getId();
        assertNotNull(id);
        Course savedCourse = courseRepository.findById(id).orElse(null);
        assertNotNull(savedCourse);
        checkCourseEqual(newCourse, savedCourse);
    }

    @Test
    @Transactional
    public void testDeleteCourseByID() {
        Course newCourse = createSampleCourse();
        Course savedCourse = courseRepository.save(newCourse);
        int id = savedCourse.getId();
        assertNotNull(savedCourse);
        assertNotNull(id);
        courseRepository.deleteById(id);
        savedCourse = courseRepository.findById(id).orElse(null);
        assertNull(savedCourse);
    }

    @Test
    @Transactional
    void findByFaculty() {
        Course newCourse = createSampleCourse();
        courseRepository.save(newCourse);
        Course diffCourse = createSampleCourse();
        diffCourse.setCourseID("ECSE-429");
        courseRepository.save(diffCourse);
        List<Course> engCourses = courseRepository.findByFaculty(FACULTY);
        assertEquals(engCourses.size(), 2);
    }

    @Test
    @Transactional
    void findByStudent() {
        Course newCourse = createSampleCourse();
        courseRepository.save(newCourse);
        Student newStudent = createTempStudent();
        newCourse.addStudent(newStudent);
        courseRepository.save(newCourse);
        List<Course> courses = courseRepository.findByStudentsId(newStudent.getId());
        assertEquals(1, courses.size());
        checkCourseEqual(courses.get(0), newCourse);
        newStudent = studentRepository.findById(newStudent.getId()).orElse(null);
        assertNotNull(newStudent);
        assertEquals(newStudent.getCourses().size(), 1);
    }


    void checkCourseEqual(Course course1, Course course2) {
        assertEquals(course1.getCourseID(), course2.getCourseID());
        assertEquals(course1.getYear(), course2.getYear());
        assertEquals(course1.getFaculty(), course2.getFaculty());
        assertEquals(course1.getSemester(), course2.getSemester());
        assertEquals(course1.getCourseSection(), course2.getCourseSection());
        Set<Student> course1Students = course1.getStudents();
        Set<Student> course2Students = course2.getStudents();
        assertEquals(course1Students, course2Students);
    }

    Student createTempStudent() {
        Account account = new Account();
        account.setEmail("test@mail.mcgill.ca");
        account.setInstitution("Institution");
        account.setName("testFullname");
        account.setPassword("password");
        account.setUserName("testusername");
        Student newStudent = new Student();
        account.setUserRole(newStudent);
        newStudent.setAccount(account);
        newStudent = studentRepository.save(newStudent);
        assertNotNull(account);
        return newStudent;
    }

    Course createSampleCourse() {
        Course course = new Course();
        course.setCourseID(COURSE_ID);
        course.setCourseSection(SECTION);
        course.setFaculty(FACULTY);
        course.setSemester(SEMESTER);
        course.setYear(YEAR);
        return course;
    }

}
