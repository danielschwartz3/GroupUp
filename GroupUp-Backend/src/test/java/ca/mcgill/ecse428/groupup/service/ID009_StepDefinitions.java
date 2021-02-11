package ca.mcgill.ecse428.groupup.service;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class ID009_StepDefinitions {

    @Given("^valid username (.+) and password (.+) $")
    public void valid_username_and_password(String username, String password) throws Throwable {
        throw new PendingException();
    }

    @When("^the user requests to add a new course (.+)$")
    public void the_user_requests_to_add_a_new_course(String newcourse) throws Throwable {
        throw new PendingException();
    }

    @Then("^the course (.+) is added to the system$")
    public void the_course_is_added_to_the_system(String newcourse) throws Throwable {
        throw new PendingException();
    }

    @Then("^a \"([^\"]*)\" message is issued to the user$")
    public void a_something_message_is_issued_to_the_user(String strArg1) throws Throwable {
        throw new PendingException();
    }

    @Then("^an error message \"([^\"]*)\" is issued to the user$")
    public void an_error_message_something_is_issued_to_the_user(String strArg1) throws Throwable {
        throw new PendingException();
    }

    @And("^the user is logged in$")
    public void the_user_is_logged_in() throws Throwable {
        throw new PendingException();
    }

    @And("^the course (.+) does not exist$")
    public void the_course_does_not_exist(String newcourse) throws Throwable {
        throw new PendingException();
    }

    @And("^the course (.+) is valid$")
    public void the_course_is_valid(String newcourse) throws Throwable {
        throw new PendingException();
    }

    @And("^the user is registered to the course$")
    public void the_user_is_registered_to_the_course() throws Throwable {
        throw new PendingException();
    }

    @And("^the course (.+) already exist in the system$")
    public void the_course_already_exist_in_the_system(String newcourse) throws Throwable {
        throw new PendingException();
    }

    @And("^the system should register the user to the course$")
    public void the_system_should_register_the_user_to_the_course() throws Throwable {
        throw new PendingException();
    }

    @And("^add the user to GroupUp chat of the course$")
    public void add_the_user_to_groupup_chat_of_the_course() throws Throwable {
        throw new PendingException();
    }

    @And("^the course (.+) is invalid format$")
    public void the_course_is_invalid_format(String newcourse) throws Throwable {
        throw new PendingException();
    }

}