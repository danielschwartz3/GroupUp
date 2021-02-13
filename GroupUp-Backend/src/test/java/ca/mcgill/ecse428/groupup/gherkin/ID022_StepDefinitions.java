package ca.mcgill.ecse428.groupup.gherkin;

import java.util.List;

import ca.mcgill.ecse428.groupup.model.Course;
import ca.mcgill.ecse428.groupup.service.*;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ID022_StepDefinitions {
	CourseService testService = new CourseService();
	List<Course> allCourses = null;
    @Given("^valid username (.+) and password (.+) $")
    public void valid_username_and_password(String username, String password) throws Throwable {
        throw new PendingException();
    }

    @Given("^the following courses exist:$")
    public void the_following_courses_exist() throws Throwable { //get all the courses for later
        allCourses = testService.getAllCourses();
    }

    @When("^the user requests view available courses in \"([^\"]*)\" semester(s) in \"([^\"]*)\" year$")
    public void the_user_requests_view_available_courses_in_something_semesters_in_something_year(String strArg1, String strArg2) throws Throwable {
        throw new PendingException();
    }

    @Then("^the user will see:$")
    public void the_user_will_see() throws Throwable {
        throw new PendingException();
    }

    @And("^the user is logged in$")
    public void the_user_is_logged_in() throws Throwable {
        throw new PendingException();
    }

}