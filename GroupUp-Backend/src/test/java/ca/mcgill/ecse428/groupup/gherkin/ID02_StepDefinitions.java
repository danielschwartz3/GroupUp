package ca.mcgill.ecse428.groupup.gherkin;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

import org.junit.Assert;

import ca.mcgill.ecse428.groupup.service.*;
import ca.mcgill.ecse428.groupup.model.*;

public class ID02_StepDefinitions {
	
	AccountService testService = new AccountService();
	Account testAccount = null;
    @Given("^valid email (.+) and password (.+) $")
    public void valid_email_and_password(String email, String password) throws Throwable {
        try { //using login to check validity, and a student membership, not great but it works
        	testAccount = testService.LogIn(email, password);
        }
        catch(IllegalArgumentException e) {
        	System.out.println(e);
        	Assert.fail("Should not throw an exception, email and password should be valid");
        }
    }

    @Given("^a non-recognized email (.+)$")
    public void a_nonrecognized_email(String email) throws Throwable {
        try {
        	testService.getAccountByID(email);
        	Assert.fail("Should through an error since the email doesn't exist");
        }
        catch(IllegalArgumentException e) {
        }
    }

    @Given("^a valid email (.+) $")
    public void a_valid_username(String email) throws Throwable {
        try {
        	testAccount = testService.getAccountByID(email);
        }
        catch(IllegalArgumentException e) {
        	Assert.fail("Should through an error since the email doesn't exist");
        }
    }

    @When("^the user requests access to the GroupUp system$")//removed a duplicate here, one without "the" will need to find dup in feature file
    public void the_user_requests_access_to_the_groupup_system() throws Throwable {
    	//what do I even do here
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
    public void a_related_student_privileges() throws Throwable {
        if (!(testAccount.getUserRole() instanceof Student)) {
        	Assert.fail("Don't have student role");
        }
    }

    @And("^a related admin privileges (.+)$")
    public void a_related_admin_privileges() throws Throwable {
        if (!(testAccount.getUserRole() instanceof Student)) {
        	Assert.fail("Don't have student role");
        }
    }


    @But("^an incorrect corresponding password $")
    public void an_incorrect_corresponding_password(String password) throws Throwable {
    	String email = testAccount.getEmail();
        try { //using login to check validity
        	testAccount = testService.LogIn(email, password);
        }
        catch(IllegalArgumentException e) {
        	assertEquals(e.getMessage(),"Password is incorrect.");
        }
    }

}