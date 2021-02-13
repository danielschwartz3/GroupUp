package ca.mcgill.ecse428.groupup.gherkin;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ID011_StepDefinitions {

    @Given("^valid username (.+) and password (.+) $")
    public void valid_username_and_password(String username, String password) throws Throwable {
        throw new PendingException();
    }

    @Given("^the user is enrolled in the following courses:$")
    public void the_user_is_enrolled_in_the_following_courses() throws Throwable {
        throw new PendingException();
    }

    @Given("^the user is not enrolled in any courses$")
    public void the_user_is_not_enrolled_in_any_courses() throws Throwable {
        throw new PendingException();
    }

    @When("^the user requests view enrolled courses$")
    public void the_user_requests_view_enrolled_courses() throws Throwable {
        throw new PendingException();
    }

    @Then("^the user will see currently enrolled courses$")
    public void the_user_will_see_currently_enrolled_courses() throws Throwable {
        throw new PendingException();
    }

    @Then("^the system will notify the user \"([^\"]*)\"$")
    public void the_system_will_notify_the_user_something(String strArg1) throws Throwable {
        throw new PendingException();
    }

    @And("^the user is logged in$")
    public void the_user_is_logged_in() throws Throwable {
        throw new PendingException();
    }

}