package ca.mcgill.ecse428.groupup.gherkin;

import ca.mcgill.ecse428.groupup.dao.AdminRepository;
import ca.mcgill.ecse428.groupup.dao.CourseRepository;
import ca.mcgill.ecse428.groupup.model.Admin;
import ca.mcgill.ecse428.groupup.utility.Semester;
import io.cucumber.java.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Course;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.service.AccountService;
import ca.mcgill.ecse428.groupup.service.CourseService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class EditCourseStepDefinition {
    @Autowired
    private AccountService accountService;

    @Autowired
    private  CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AdminRepository adminRepository;

    private static Account adminAccount;

    private static Course createdCourse, updatedCourse;

    @Given("valid administrator's username {string} name {string} email {string} institution {string} and password {string}")
    public void createValidAdmin(String username, String name ,String email, String institution, String password) {

        adminAccount = accountService.createAdminAccount(new Admin(), username, name, email, institution, password);

    }
    @And("the administrator is logged in")
    public void verifyAdminLoggedIn() {
        assertNotNull(adminAccount);

    }

    @Given("a course exists with course ID {string}, name {string}, semester {string}, year {string}, section {string}, faculty {string}")
    public void createCourse(String old_course_ID, String old_course_name, String old_course_semester, String old_course_year, String old_course_section, String old_faculty) {
        try {
           createdCourse = courseService.createCourse(old_course_ID, old_faculty, old_course_semester, old_course_year, old_course_section, old_course_name);
        } catch (Exception e) {
          String message = e.getMessage();
          String errorMessage = "This course with courseID: " + old_course_ID + " section: " + old_course_section + " already exists for the " + old_course_semester + old_course_year + " semester ";
          assertTrue(message.equalsIgnoreCase(errorMessage));

          createdCourse = courseService.getCourseByCourseID(old_course_ID).get(0);
        }

    }

    @When("the administrator attempts to changes the information for course {string} to name {string}, semester {string}, year {string}, section {string}, faculty {string}")
    public void changeCourseInformation(String old_course_ID, String new_course_name, String new_course_semester, String new_course_year, String new_course_section, String new_faculty) {

        Course course = new Course();
        course.setCourseID(old_course_ID);
        course.setFaculty(new_faculty);
        course.setCourseSection(new_course_section);
        course.setYear(new_course_year);
        course.setSemester(Semester.valueOf(new_course_semester.toUpperCase()));
        course.setCourseName(new_course_name);

        updatedCourse = courseService.updateCourse(createdCourse.getId(), course);

    }

    @Then("the course with with course ID {string}, has name {string}, semester {string}, year {string}, section {string}, faculty {string}")
    public void verifyCourseUpdate(String old_course_ID, String new_course_name, String new_course_semester, String new_course_year, String new_course_section, String new_faculty) {
        assertNotNull(updatedCourse);
        assertTrue(updatedCourse.getCourseID().equalsIgnoreCase(old_course_ID));
        assertTrue(updatedCourse.getSemester().toString().equalsIgnoreCase(new_course_semester));
        assertTrue(updatedCourse.getYear().equalsIgnoreCase(new_course_year));
        assertTrue(updatedCourse.getFaculty().equalsIgnoreCase(new_faculty));
        assertTrue(updatedCourse.getCourseName().equalsIgnoreCase(new_course_name));
    }

    @Before
    @After
    public void clearDatabase() {
        // Clear the table to avoid inconsistency
        System.out.println("Clearing database in between tests");
        courseRepository.deleteAll();
        adminRepository.deleteAll();
    }

}
