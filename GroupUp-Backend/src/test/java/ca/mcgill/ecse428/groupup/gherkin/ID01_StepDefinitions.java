//package ca.mcgill.ecse428.groupup.gherkin;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.Assert.assertNotNull;
//
//import org.junit.Assert;
//
//import ca.mcgill.ecse428.groupup.model.Account;
//import ca.mcgill.ecse428.groupup.model.Course;
//import ca.mcgill.ecse428.groupup.model.Student;
//import ca.mcgill.ecse428.groupup.service.AccountService;
//import ca.mcgill.ecse428.groupup.service.CourseService;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//public class ID01_StepDefinitions {
//	//the login + autogeneration literally seems to be invalid by the service layer that requires not null inputs
//	Account testAccount = null;
//	Course mycourse = null;
//	String errorMessage = null;
//	String testEmail = null;
//	String testName = null;
////	Student testStudent = null;
//	AccountService testAccountService = new AccountService();
//	CourseService testCourseService = new CourseService();
//	
//    @Given("student {word} with student email {word} and institution name {string} is student in good standing")
//    public void studentUser_nameWithStudentEmailUser_emailAndInstitutionNameUser_institutionIsStudentInGoodStanding(String name, String email, String institutionName) {
////    	testAccount = testAccountService.createStudentAccount(new Student(), "username", name, email, "institutionName", "password");
//    	testName = name;
//    	testEmail = email;
//    	return; 
//    }
//
//    @When("student {word} requests user access to the GroupUp System")
//    public void studentUser_nameRequestsUserAccessToTheGroupUpSystem(String username) {
//        try {
//        	testAccount = testAccountService.createStudentAccount(new Student(), username, testName, testEmail, "institutionName", "password");
//        }
//        catch(Exception e) {
//        	Assert.fail();
//        }
//    	
////    	testAccountService.LogIn(testAccount.getEmail(), testAccount.getPassword());
//    	return;
//    }
//
//    @Then("a new {word} and initial {word} are generated")
//    public void aNewUser_nameAndInitialPasswordAreGenerated(String username, String password) {
//        assertNotNull(testAccountService.getAccountByID(testEmail));    	
//    	return;
//    }
//    
//    //I think I just don't understand this requirement but from my understand service layer doesn't check for role, so it wouldn't be allowed->if I get it
//    @Given("John Doe uses email INVALID_EMAIL to request to be a registered user")
//    public void johnDoeUsesEmailINVALID_EMAILToRequestToBeARegisteredUser() {
//       String email = "invalid";
//       testName = "John Doe";
//       testEmail = email;
//    }
//
//    @When("John Doe requests user access to the GroupUp System")
//    public void johnDoeRequestsUserAccessToTheGroupUpSystem() {
//        try {
//        	testAccount = testAccountService.createStudentAccount(new Student(), "username", testName, testEmail, "institutionName", "password");
//        }
//        catch(Exception e) {
//        	errorMessage = e.getMessage();
//        }
//    	
//    	return;
//    }
//
//    @Then("John Doe will be notified of an unverified student request")
//    public void johnDoeWillBeNotifiedOfAnUnverifiedStudentRequest() {
//        assertEquals("Unverified Student Request", errorMessage);
//    	return;
//    }
//
//    @Given("James Smith is a user of the GoupUp System")
//    public void jamesSmithIsAUserOfTheGoupUpSystem() {
//        testAccount = testAccountService.createStudentAccount(new Student(), "username", "James Smith", "valid@email.com", "mcgill", "1234");
//    }
//
//    @When("James Smith requests user access to the GroupUp System")
//    public void jamesSmithRequestsUserAccessToTheGroupUpSystem() {
//    	try {
//    		testAccountService.createStudentAccount(new Student(), "username", "James Smith", "valid@email.com", "mcgill", "1234"); //already created
//    	}
//    	catch(IllegalArgumentException e) {
////    		assertEquals("Already registered",e.getMessage());
//    		errorMessage = e.getMessage();
//    	}
//    }
//    
//    @Then("James Smith will be notified of an unverified student request")
//    public void jamesSmithWillBeNotifiedThatHeIsAlreadyRegistered() {
//        assertEquals("Already Registered", errorMessage);
//    	return;
//    }
//}
