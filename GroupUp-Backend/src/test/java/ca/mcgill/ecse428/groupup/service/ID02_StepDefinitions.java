package ca.mcgill.ecse428.groupup.service;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.java.en.But;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class ID02_StepDefinitions {

    @Given("^valid username (.+) and password (.+) $")
    public void valid_username_and_password(String username, String password) throws Throwable {
        throw new PendingException();
    }

    @Given("^a non-recognized username (.+)$")
    public void a_nonrecognized_username(String username) throws Throwable {
        throw new PendingException();
    }

    @Given("^a valid username (.+) $")
    public void a_valid_username(String username) throws Throwable {
        throw new PendingException();
    }

    @When("^user (.+) requests access to the GroupUp system$")
    public void user_requests_access_to_the_groupup_system(String username) throws Throwable {
        throw new PendingException();
    }

    @When("^the user requests access to the GroupUp system$")
    public void the_user_requests_access_to_the_groupup_system() throws Throwable {
        throw new PendingException();
    }

    @Then("^they will be granted access to the GroupUp system as a student$")
    public void they_will_be_granted_access_to_the_groupup_system_as_a_student() throws Throwable {
        throw new PendingException();
    }

    @Then("^an \"([^\"]*)\" message is issued$")
    public void an_something_message_is_issued(String strArg1) throws Throwable {
        throw new PendingException();
    }

    @And("^a related student privileges (.+)$")
    public void a_related_student_privileges(String role) throws Throwable {
        throw new PendingException();
    }

    @And("^a related admin privileges (.+)$")
    public void a_related_admin_privileges(String role) throws Throwable {
        throw new PendingException();
    }

    @And("^a record of the attempt is sent to the System Administrator$")
    public void a_record_of_the_attempt_is_sent_to_the_system_administrator() throws Throwable {
        throw new PendingException();
    }

    @But("^an incorrect corresponding password $")
    public void an_incorrect_corresponding_password() throws Throwable {
        throw new PendingException();
    }

}