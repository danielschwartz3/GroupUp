package ca.mcgill.ecse428.groupup.service;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.junit.Cucumber;

public class ID01_StepDefinitions {

    @Given("^student (.+) with student email(.+) and institution name (.+) is student in good standing$")
    public void student_with_student_email_and_institution_name_is_student_in_good_standing(String username, String useremail, String userinstitution) throws Throwable {
        throw new PendingException();
    }

    @Given("^John Doe uses email INVALID_EMAIL to request to be a registered user $")
    public void john_doe_uses_email_invalidemail_to_request_to_be_a_registered_user() throws Throwable {
        throw new PendingException();
    }

    @Given("^James Smith is a user of the GoupUp System$")
    public void james_smith_is_a_user_of_the_goupup_system() throws Throwable {
        throw new PendingException();
    }

    @When("^student (.+) requests user access to the GroupUp System$")
    public void student_requests_user_access_to_the_groupup_system(String username) throws Throwable {
        throw new PendingException();
    }

    @When("^John Doe requests user access to the GroupUp System$")
    public void john_doe_requests_user_access_to_the_groupup_system() throws Throwable {
        throw new PendingException();
    }

    @When("^James Smith requests user access to the GroupUp System$")
    public void james_smith_requests_user_access_to_the_groupup_system() throws Throwable {
        throw new PendingException();
    }

    @Then("^a new (.+) and initial (.+) are generated$")
    public void a_new_and_initial_are_generated(String username, String password) throws Throwable {
        throw new PendingException();
    }

    @Then("^an \"([^\"]*)\" message is issued$")
    public void an_something_message_is_issued(String strArg1) throws Throwable {
        throw new PendingException();
    }

}