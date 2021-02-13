package ca.mcgill.ecse428.groupup.gherkin;

import java.util.List;

import org.junit.Assert;

import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Course;
import ca.mcgill.ecse428.groupup.service.*;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ID022_StepDefinitions {
	CourseService testCourseService = new CourseService();
	AccountService testAccountService = new AccountService();
	List<Course> allCourses = null;
	List<Course> requestCourses = null;
	Account testAccount = null;
	
    @Given("^valid email (.+) and password (.+) $")
    public void valid_email_and_password(String email, String password) throws Throwable { //duplicate, ask shudy about how to remove 
        try { 
        	testAccount = testAccountService.LogIn(email, password);
        }
        catch(IllegalArgumentException e) {
        	System.out.println(e);
        	Assert.fail("Should not throw an exception, email and password should be valid");
        }
    }

    @Given("^the following courses exist:$")
    public void the_following_courses_exist() throws Throwable { //get all the courses for later
        allCourses = testCourseService.getAllCourses(); 
    }

    @When("^the user requests view available courses in \"([^\"]*)\" semester(s) in \"([^\"]*)\" year$")
    public void the_user_requests_view_available_courses_in_something_semesters_in_something_year(String strArg1, String strArg2) throws Throwable {
    	requestCourses = testCourseService.getAllCourses(); 
    }
    

    @Then("^the user will see:$")
    public void the_user_will_see() throws Throwable {
        if (requestCourses == null) {//try to think of more assertive test
        	Assert.fail("The requested courses did not match the examples, should not be null");
        }
    }

    @And("^the user is logged in$")
    public void the_user_is_logged_in() throws Throwable {
        if (testAccount == null) {
        	Assert.fail("User is not logged in");
        }
    }

}