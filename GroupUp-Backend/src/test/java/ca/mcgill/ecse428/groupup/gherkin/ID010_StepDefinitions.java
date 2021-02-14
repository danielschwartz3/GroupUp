//package ca.mcgill.ecse428.groupup.gherkin;
//
//import io.cucumber.java.PendingException;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.But;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import static org.junit.Assert.*;
//
//import java.util.Set;
//
//import org.junit.Assert;
//
//import ca.mcgill.ecse428.groupup.service.*;
//import ca.mcgill.ecse428.groupup.model.*;
////go back to feature for this one and add a background
//public class ID010_StepDefinitions {
//	Account testAccount = null;
//	Course mycourse = null;
//	AccountService testAccountService = new AccountService();
//	CourseService testCourseService = new CourseService();
//    @Given("^valid email (.+) and password (.+)$")
//    public void valid_email_and_password(String email, String password) throws Throwable {
//    	testAccount = testAccountService.createStudentAccount(new Student(), "No matter", "Ben", email, "McGill", password); //must be valid
//    }
//
//    @Given("^the course (.+) exist$")
//    public void the_course_exist(String course) throws Throwable { //duplicate 009
//    	testCourseService.createCourse(course, "no matter", "no matter", "no matter", "no matter", "no matter");
//    }
//
//    @When("^user (.+) requests register for course (.+)$") 
//    public void user_requests_register_for_course(String email, String course) throws Throwable { //we need to decide if course id is a int, if not I need find course by name
//    	mycourse = testCourseService.getCourseByCourseID(course).get(0);
//        testCourseService.registerStudent((Student) testAccount.getUserRole(), mycourse);
//    }
//
//    @Then("^the user will be registered undered the course$")
//    public void the_user_will_be_registered_undered_the_course() throws Throwable {
//        Student studentAccount = (Student) testAccount.getUserRole();
//        if (!studentAccount.getCourses().contains(mycourse)) {
//        	Assert.fail("The user did not get registered");
//        }
//    }
//
//    @Then("^the user will be notified that user is not logged in$")
//    public void the_user_will_be_notified_that_user_is_not_logged_in() throws Throwable {
//        try {
//        	testCourseService.registerStudent((Student) testAccount.getUserRole(), mycourse);
//        }
//        catch(Exception e) {
//        	Assert.assertEquals("Student does not exist", e.getMessage());
//        }
//    }
//
//    @Then("^the user will be notified that the course does not exist$")
//    public void the_user_will_be_notified_that_the_course_does_not_exist() throws Throwable {
//        try {
//        	testCourseService.registerStudent((Student) testAccount.getUserRole(), mycourse);
//        }
//        catch(Exception e) {
//        	Assert.assertEquals("Course does not exist", e.getMessage());
//        }
//    }
//
//    @And("^the user is logged in$")
//    public void the_user_is_logged_in() throws Throwable {
//        testAccountService.LogIn(testAccount.getEmail(), testAccount.getPassword());
//    }
//
//    @And("^the user is not logged in$")
//    public void the_user_is_not_logged_in() throws Throwable {
//        testAccount = null;
//    }
//
//    @And("^the course (.+) doesn't exist$")
//    public void the_course_doesnt_exist(String course) throws Throwable {
//        mycourse = null; //we know null isn't a registered course
//    }
//
//}