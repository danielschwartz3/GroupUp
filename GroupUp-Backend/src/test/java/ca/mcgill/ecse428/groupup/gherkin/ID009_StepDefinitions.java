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

public class ID009_StepDefinitions {
	Account testAccount = null;
	AccountService testAccountService = new AccountService();
	CourseService testCourseService = new CourseService();
	String coursename = null;
	String error = "";
    @Given("^valid email (.+) and password (.+) $")
    public void valid_email_and_password(String email, String password) throws Throwable {
    	testAccount = testAccountService.createStudentAccount(new Student(), "No matter", "Ben", email, "McGill", password); //must be valid
    }
    
    @Given("^the course (.+) is invalid format$")
    public void the_course_is_invalid_format(String newcourse) throws Throwable {
        if (newcourse != "") { //really the only invalid format for a course item now
        	coursename = "";
        }
        else {
        	coursename = newcourse;
        }
    }
    @Given("^the course (.+) already exist in the system$")
    public void the_course_already_exist_in_the_system(String newcourse) throws Throwable {
        testCourseService.createCourse(newcourse, "no matter", "no matter", "no matter", "no matter", "no matter");
    }

    @When("^the user requests to add a new course (.+)$")
    public void the_user_requests_to_add_a_new_course(String newcourse) throws Throwable {
    	try {
    		testCourseService.createCourse(newcourse, "no matter", "no matter", "no matter", "no matter", "no matter");
    	}
    	catch(Exception e) {
    		error = e.getMessage();
    		Assert.fail();
    	}
    }

    @Then("^the course (.+) is added to the system$")
    public void the_course_is_added_to_the_system(String newcourse) throws Throwable {
    	try {
    		Course createdCourse = testCourseService.createCourse(newcourse, "no matter", "no matter", "no matter", "no matter", "no matter"); //create course needs an override method to take a student who is making it to also register
    		if (createdCourse.getCourseID() != newcourse) {
    			Assert.fail();
    		}
    	}
    	catch(Exception e) {
    		Assert.fail();
    	}
    }

    @Then("^a \"([^\"]*)\" message is issued to the user$")
    public void a_something_message_is_issued_to_the_user(String strArg1) throws Throwable {
    	if (!strArg1.equals(error)) {
    		Assert.fail();
    	}
    }

    @Then("^an error message \"([^\"]*)\" is issued to the user$") //should be able to remove one of these
    public void an_error_message_something_is_issued_to_the_user(String strArg1) throws Throwable {
    	if (!strArg1.equals(error)) {
    		Assert.fail();
    	}
    }

    @And("^the user is logged in$")
    public void the_user_is_logged_in() throws Throwable {
        testAccountService.LogIn(testAccount.getEmail(), testAccount.getPassword());
    }


    @And("^the user is registered to the course$")
    public void the_user_is_registered_to_the_course() throws Throwable {
        throw new PendingException(); //no mechanism to allow for this right now, register automatically during course creation
    }


    @And("^the system should register the user to the course$") //remove dup -> also doesnt work for the same reason
    public void the_system_should_register_the_user_to_the_course() throws Throwable {
        throw new PendingException();
    }

    @And("^add the user to GroupUp chat of the course$") //no service mechanism for this unless I didn't see it
    public void add_the_user_to_groupup_chat_of_the_course() throws Throwable {
        throw new PendingException();
    }

}