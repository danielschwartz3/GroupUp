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
//public class ID012_StepDefinitions {
//	AccountService testAccountService = new AccountService();
//	CourseService testCourseService = new CourseService();
//	Account testAccount = null;
//	Course c = null;
//    @Given("^valid email (.+) and password (.+) $") //find a way to remove duplicates 
//    public void valid_email_and_password(String email, String password) throws Throwable {
//    	testAccount = testAccountService.createStudentAccount(new Student(), "No matter", "Ben", email, "McGill", password); //must be valid, since I am making this
//    }
//
//    @Given("^the user is registered for this (.+)$")
//    public void the_user_is_registered_for_this(String course) throws Throwable {
//        throw new PendingException(); //ask about get enrolled course, or how to get courses registered for a user??
//    }
//
//    @When("^the user requests to deregister from this (.+)$")
//    public void the_user_requests_to_deregister_from_this(String course) throws Throwable {
//    	throw new PendingException(); //id of course should be string?? 
//    }
//
//    @When("^the user requests to de-register from the (.+) they are not registered for$")
//    public void the_user_requests_to_deregister_from_the_they_are_not_registered_for(String course) throws Throwable {
//        throw new PendingException();
//    }
//
//    @Then("^they will no longer be enrolled$")
//    public void they_will_no_longer_be_enrolled() throws Throwable {
//        throw new PendingException();
//    }
//
//    @Then("^a \"([^\"]*)\" message is issued$")
//    public void a_something_message_is_issued(String strArg1) throws Throwable {
//        throw new PendingException();
//    }
//
//    @And("^the user is logged in$")
//    public void the_user_is_logged_in() throws Throwable {
//        try{
//        	testAccountService.LogIn(testAccount.getEmail(), testAccount.getPassword());
//        }
//        catch(Exception e) {
//        	Assert.fail("Login was not allowed");
//        }
//    }
//
//}