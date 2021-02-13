package ca.mcgill.ecse428.groupup.gherkin;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ID010_StepDefinitions {

    @Given("^valid username (.+) and password (.+) $")
    public void valid_username_and_password(String username, String password) throws Throwable {
        throw new PendingException();
    }

    @Given("^the course (.+) exist$")
    public void the_course_exist(String course) throws Throwable {
        throw new PendingException();
    }

    @When("^user (.+) requests register for course (.+)$")
    public void user_requests_register_for_course(String username, String course) throws Throwable {
        throw new PendingException();
    }

    @Then("^the user will be registered undered the course$")
    public void the_user_will_be_registered_undered_the_course() throws Throwable {
        throw new PendingException();
    }

    @Then("^the user will be notified that user is not logged in$")
    public void the_user_will_be_notified_that_user_is_not_logged_in() throws Throwable {
        throw new PendingException();
    }

    @Then("^the user will be notified that the course does not exist$")
    public void the_user_will_be_notified_that_the_course_does_not_exist() throws Throwable {
        throw new PendingException();
    }

    @And("^the user is logged in$")
    public void the_user_is_logged_in() throws Throwable {
        throw new PendingException();
    }

    @And("^the user is not logged in$")
    public void the_user_is_not_logged_in() throws Throwable {
        throw new PendingException();
    }

    @And("^the course (.+) doesn't exist$")
    public void the_course_doesnt_exist(String course) throws Throwable {
        throw new PendingException();
    }

}