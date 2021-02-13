package ca.mcgill.ecse428.groupup.service;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class ID022_StepDefinitions {

    @Given("^valid username (.+) and password (.+) $")
    public void valid_username_and_password(String username, String password) throws Throwable {
        throw new PendingException();
    }

    @Given("^the following courses exist:$")
    public void the_following_courses_exist() throws Throwable {
        throw new PendingException();
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