package ca.mcgill.ecse428.groupup.service;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;

public class ID012_StepDefinitions {

    @Given("^valid username (.+) and password (.+) $")
    public void valid_username_and_password(String username, String password) throws Throwable {
        throw new PendingException();
    }

    @Given("^the user is registered for this (.+)$")
    public void the_user_is_registered_for_this(String course) throws Throwable {
        throw new PendingException();
    }

    @When("^the user requests to deregister from this (.+)$")
    public void the_user_requests_to_deregister_from_this(String course) throws Throwable {
        throw new PendingException();
    }

    @When("^the user requests to de-register from the (.+) they are not registered for$")
    public void the_user_requests_to_deregister_from_the_they_are_not_registered_for(String course) throws Throwable {
        throw new PendingException();
    }

    @Then("^they will no longer be enrolled$")
    public void they_will_no_longer_be_enrolled() throws Throwable {
        throw new PendingException();
    }

    @Then("^a \"([^\"]*)\" message is issued$")
    public void a_something_message_is_issued(String strArg1) throws Throwable {
        throw new PendingException();
    }

    @And("^the user is logged in$")
    public void the_user_is_logged_in() throws Throwable {
        throw new PendingException();
    }

}