//package ca.mcgill.ecse428.groupup.gherkin;
//
//import io.cucumber.java.PendingException;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.But;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import static org.junit.Assert.*;
//
//import org.junit.Assert;
//
//import ca.mcgill.ecse428.groupup.service.*;
//import ca.mcgill.ecse428.groupup.model.*;
//
//public class ID02_StepDefinitions {
//	
//	AccountService testService = new AccountService();
//	Account testAccount = null;
//	String nonValidEmail = null; //no @ so must be invalid
//    @Given("^valid email (.+) and password (.+) $")
//    public void valid_email_and_password(String email, String password) throws Throwable { //ie. an existing student account
//    	testAccount = testService.createStudentAccount(new Student(), "No matter", "Ben", email, "McGill", password); //must be valid
//    }
//
//    @Given("^a non-recognized email (.+)$")
//    public void a_nonrecognized_email(String email) throws Throwable {
//    	nonValidEmail = "Ben"; //no @ so must be invalid
//    }
//
//    @Given("^a valid email (.+) $")
//    public void a_valid_username(String email) throws Throwable {
//    	testAccount = testService.createStudentAccount(new Student(), "No matter", "Ben", email, "McGill", "No matter"); //must be valid
//    }
//
//    @When("^the user requests access to the GroupUp system$")//removed a duplicate here, one without "the" will need to find dup in feature file
//    public void the_user_requests_access_to_the_groupup_system() throws Throwable {
//    	//check if request works with no error
//        try{
//        	testService.LogIn(testAccount.getEmail(), testAccount.getPassword());
//        }
//        catch(Exception e) {
//        	Assert.fail("Request was not inputed");
//        }
//    }
//
//    @Then("^they will be granted access to the GroupUp system as a student$")
//    public void they_will_be_granted_access_to_the_groupup_system_as_a_student() throws Throwable {
//        try{
//        	Account tempAccount = testService.LogIn(testAccount.getEmail(), testAccount.getPassword());
//        	if (tempAccount != testAccount) {
//        		Assert.fail();
//        	}
//        }
//        catch(Exception e) {
//        	Assert.fail("Request was not inputed");
//        }
//    }
//
//    @Then("^an \"([^\"]*)\" message is issued$")
//    public void an_something_message_is_issued(String strArg1) throws Throwable {
//        try{
//        	testService.LogIn(testAccount.getEmail(), testAccount.getPassword());
//        }
//        catch(Exception e) {
//        	if (e.getMessage() != "Password is incorrect.") {
//        		Assert.fail("wrong error message");
//        	}
//        }
//    }
//
//    @And("^a related student privileges (.+)$")
//    public void a_related_student_privileges() throws Throwable {
//        if (!(testAccount.getUserRole() instanceof Student)) {
//        	Assert.fail("Don't have student role");
//        }
//    }
//
//    @And("^a related admin privileges (.+)$")
//    public void a_related_admin_privileges() throws Throwable {
//        if (!(testAccount.getUserRole() instanceof Student)) {
//        	Assert.fail("Don't have student role");
//        }
//    }
//
//
//    @But("^an incorrect corresponding password $")
//    public void an_incorrect_corresponding_password(String password) throws Throwable {
//    	if (password == testAccount.password) {
//    		Assert.fail("password is not unqiue");
//    	}
//    }
//
//}