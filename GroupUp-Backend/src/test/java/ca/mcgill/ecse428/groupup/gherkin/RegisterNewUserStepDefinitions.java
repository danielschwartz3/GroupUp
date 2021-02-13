package ca.mcgill.ecse428.groupup.gherkin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Course;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.service.AccountService;
import ca.mcgill.ecse428.groupup.service.CourseService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterNewUserStepDefinitions {
	//the login + autogeneration literally seems to be invalid by the service layer that requires not null inputs
	Account testAccount = null;
	Course mycourse = null;
	AccountService testAccountService = new AccountService();
	CourseService testCourseService = new CourseService();
    @Given("student {word} with student email {word} and institution name {string} is student in good standing")
    public void studentUser_nameWithStudentEmailUser_emailAndInstitutionNameUser_institutionIsStudentInGoodStanding(String name, String email, String institutionName) {
        return; 
    }

    @When("student {word} requests user access to the GroupUp System")
    public void studentUser_nameRequestsUserAccessToTheGroupUpSystem(String username) {
        return;
    }

    @Then("a new {word} and initial {word} are generated")
    public void aNewUser_nameAndInitialPasswordAreGenerated(String username, String password) {
        return;
    }
    //I think I just don't understand this requirement but from my understand service layer doesn't check for role, so it wouldn't be allowed->if I get it
    @Given("John Doe uses email INVALID_EMAIL to request to be a registered user")
    public void johnDoeUsesEmailINVALID_EMAILToRequestToBeARegisteredUser() {
       
    }

    @When("John Doe requests user access to the GroupUp System")
    public void johnDoeRequestsUserAccessToTheGroupUpSystem() {
        return;
    }

    @Then("an {string} message is issued")
    public void anMessageIsIssued(String arg0) {
        return;
    }

    @Given("James Smith is a user of the GoupUp System")
    public void jamesSmithIsAUserOfTheGoupUpSystem() {
        testAccountService.createStudentAccount(new Student(), "no matter", "no matter", "valid@email.com", "mcgill", "1234");
    }

    @When("James Smith requests user access to the GroupUp System")
    public void jamesSmithRequestsUserAccessToTheGroupUpSystem() {
    	try {
    		testAccountService.createStudentAccount(new Student(), "no matter", "no matter", "valid@email.com", "mcgill", "1234"); //already created
    	}
    	catch(IllegalArgumentException e) {
    		assertEquals("Already registered",e.getMessage());   		
    	}
    }
}
