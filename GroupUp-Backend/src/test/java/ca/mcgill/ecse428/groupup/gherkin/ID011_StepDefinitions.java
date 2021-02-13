package ca.mcgill.ecse428.groupup.gherkin;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Assert;

import ca.mcgill.ecse428.groupup.service.*;
import ca.mcgill.ecse428.groupup.model.*;

public class ID011_StepDefinitions {
	AccountService testService = new AccountService();
	CourseService testCourseService = new CourseService();
	Account testAccount = null;
	Course mycourse = null;
	Set<Course> requestedCourses = null;
    @Given("^valid email (.+) and password (.+) $")
    public void valid_email_and_password(String email, String password) throws Throwable {
    	testAccount = testService.createStudentAccount(new Student(), "No matter", "Ben", email, "McGill", password); //must be valid
    }

    @Given("^the user is enrolled in the following courses:$")
    public void the_user_is_enrolled_in_the_following_courses() throws Throwable {
        mycourse = testCourseService.createCourse("Hi", "my", "name", "is", "Ben", "great");
        Student aStudent = (Student) testAccount.getUserRole(); //we know its student since we made it
        testCourseService.registerStudent(aStudent, mycourse);
    }

    @Given("^the user is not enrolled in any courses$")
    public void the_user_is_not_enrolled_in_any_courses() throws Throwable {
    	Student aStudent = (Student) testAccount.getUserRole();
    	for (Course aCourse: aStudent.getCourses()) {
    		testCourseService.unregisterStudent(aStudent, aCourse);
    	}
    }

    @When("^the user requests view enrolled courses$")
    public void the_user_requests_view_enrolled_courses() throws Throwable {
    	Student aStudent = (Student) testAccount.getUserRole();
    	requestedCourses = aStudent.getCourses(); //really should be get enrolled courses, but I don;t know how to get student id
    }

    @Then("^the user will see currently enrolled courses$")
    public void the_user_will_see_currently_enrolled_courses() throws Throwable {
        if (!requestedCourses.contains(mycourse)) {
        	Assert.fail("Course not found");
        }
    }

    @Then("^the system will notify the user \"([^\"]*)\"$")
    public void the_system_will_notify_the_user_something(String strArg1) throws Throwable {
        throw new PendingException(); //here is where the problem from the when rears its head
    }

    @And("^the user is logged in$")
    public void the_user_is_logged_in() throws Throwable {
        testService.LogIn(testAccount.getEmail(), testAccount.getPassword());
    }

}