package ca.mcgill.ecse428.groupup.gherkin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Set;

import org.junit.Assert;

import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Course;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.service.AccountService;
import ca.mcgill.ecse428.groupup.service.CourseService;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {
	
//========================ID01_Register New User=======================================//
	
	//the login + autogeneration literally seems to be invalid by the service layer that requires not null inputs
	Account testAccount = null;
	Course mycourse = null;
	String errorMessage = null;
	String testEmail = null;
	String testName = null;
//	Student testStudent = null;
	AccountService testAccountService = new AccountService();
	CourseService testCourseService = new CourseService();
	
    @Given("student {word} with student email {word} and institution name {string} is student in good standing")
    public void studentUser_nameWithStudentEmailUser_emailAndInstitutionNameUser_institutionIsStudentInGoodStanding(String name, String email, String institutionName) {
//    	testAccount = testAccountService.createStudentAccount(new Student(), "username", name, email, "institutionName", "password");
    	testName = name;
    	testEmail = email;
    	return; 
    }

    @When("student {word} requests user access to the GroupUp System")
    public void studentUser_nameRequestsUserAccessToTheGroupUpSystem(String username) {
        try {
        	testAccount = testAccountService.createStudentAccount(new Student(), username, testName, testEmail, "institutionName", "password");
        }
        catch(Exception e) {
        	Assert.fail();
        }
    	
//    	testAccountService.LogIn(testAccount.getEmail(), testAccount.getPassword());
    	return;
    }

    @Then("a new {word} and initial {word} are generated")
    public void aNewUser_nameAndInitialPasswordAreGenerated(String username, String password) {
        assertNotNull(testAccountService.getAccountByID(testEmail));    	
    	return;
    }
    
    //I think I just don't understand this requirement but from my understand service layer doesn't check for role, so it wouldn't be allowed->if I get it
    @Given("John Doe uses email INVALID_EMAIL to request to be a registered user")
    public void johnDoeUsesEmailINVALID_EMAILToRequestToBeARegisteredUser() {
       String email = "invalid";
       testName = "John Doe";
       testEmail = email;
    }

    @When("John Doe requests user access to the GroupUp System")
    public void johnDoeRequestsUserAccessToTheGroupUpSystem() {
        try {
        	testAccount = testAccountService.createStudentAccount(new Student(), "username", testName, testEmail, "institutionName", "password");
        }
        catch(Exception e) {
        	errorMessage = e.getMessage();
        }
    	
    	return;
    }

    @Then("John Doe will be notified of an unverified student request")
    public void johnDoeWillBeNotifiedOfAnUnverifiedStudentRequest() {
        assertEquals("Unverified Student Request", errorMessage);
    	return;
    }

    @Given("James Smith is a user of the GoupUp System")
    public void jamesSmithIsAUserOfTheGoupUpSystem() {
        testAccount = testAccountService.createStudentAccount(new Student(), "username", "James Smith", "valid@email.com", "mcgill", "1234");
    }

    @When("James Smith requests user access to the GroupUp System")
    public void jamesSmithRequestsUserAccessToTheGroupUpSystem() {
    	try {
    		testAccountService.createStudentAccount(new Student(), "username", "James Smith", "valid@email.com", "mcgill", "1234"); //already created
    	}
    	catch(IllegalArgumentException e) {
//    		assertEquals("Already registered",e.getMessage());
    		errorMessage = e.getMessage();
    	}
    }
    
    @Then("James Smith will be notified of an unverified student request")
    public void jamesSmithWillBeNotifiedThatHeIsAlreadyRegistered() {
        assertEquals("Already Registered", errorMessage);
    	return;
    }
    
    
//=======================ID02 Login Existing User============================================//
    
    AccountService testService = new AccountService();
//	Account testAccount = null;
	String nonValidEmail = null; //no @ so must be invalid
    @Given("^valid email (.+) and password (.+) $")
    public void valid_email_and_password(String email, String password) throws Throwable { //ie. an existing student account
    	testAccount = testService.createStudentAccount(new Student(), "No matter", "Ben", email, "McGill", password); //must be valid
    }

    @Given("^a non-recognized email (.+)$")
    public void a_nonrecognized_email(String email) throws Throwable {
    	nonValidEmail = "Ben"; //no @ so must be invalid
    }

    @Given("^a valid email (.+) $")
    public void a_valid_username(String email) throws Throwable {
    	testAccount = testService.createStudentAccount(new Student(), "No matter", "Ben", email, "McGill", "No matter"); //must be valid
    }

    @When("^the user requests access to the GroupUp system$")//removed a duplicate here, one without "the" will need to find dup in feature file
    public void the_user_requests_access_to_the_groupup_system() throws Throwable {
    	//check if request works with no error
        try{
        	testService.LogIn(testAccount.getEmail(), testAccount.getPassword());
        }
        catch(Exception e) {
        	Assert.fail("Request was not inputed");
        }
    }

    @Then("^they will be granted access to the GroupUp system as a student$")
    public void they_will_be_granted_access_to_the_groupup_system_as_a_student() throws Throwable {
        try{
        	Account tempAccount = testService.LogIn(testAccount.getEmail(), testAccount.getPassword());
        	if (tempAccount != testAccount) {
        		Assert.fail();
        	}
        }
        catch(Exception e) {
        	Assert.fail("Request was not inputed");
        }
    }

    @Then("^an \"([^\"]*)\" message is issued$")
    public void an_something_message_is_issued(String strArg1) throws Throwable {
        try{
        	testService.LogIn(testAccount.getEmail(), testAccount.getPassword());
        }
        catch(Exception e) {
        	if (e.getMessage() != "Password is incorrect.") {
        		Assert.fail("wrong error message");
        	}
        }
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
    	if (password == testAccount.password) {
    		Assert.fail("password is not unqiue");
    	}
    }
    
    
//=======================ID009 Add New Course===========================================//
//    Account testAccount = null;
//	AccountService testAccountService = new AccountService();
//	CourseService testCourseService = new CourseService();
	String coursename = null;
	String error = "";
//    @Given("^valid email (.+) and password (.+) $")
//    public void valid_email_and_password(String email, String password) throws Throwable {
//    	testAccount = testAccountService.createStudentAccount(new Student(), "No matter", "Ben", email, "McGill", password); //must be valid
//    }
    
    @Given("^the course (.+) is invalid format$")
    public void the_course_is_invalid_format(String newcourse) throws Throwable {
        if (newcourse != "") { //really the only invalid format for a course item now
        	coursename = "";
        }
        else {
        	coursename = newcourse;
        }
    }
    @Given("^the course (.+) already exist in the system$")
    public void the_course_already_exist_in_the_system(String newcourse) throws Throwable {
        testCourseService.createCourse(newcourse, "no matter", "no matter", "no matter", "no matter", "no matter");
    }

    @When("^the user requests to add a new course (.+)$")
    public void the_user_requests_to_add_a_new_course(String newcourse) throws Throwable {
    	try {
    		testCourseService.createCourse(newcourse, "no matter", "no matter", "no matter", "no matter", "no matter");
    	}
    	catch(Exception e) {
    		error = e.getMessage();
    		Assert.fail();
    	}
    }

    @Then("^the course (.+) is added to the system$")
    public void the_course_is_added_to_the_system(String newcourse) throws Throwable {
    	try {
    		Course createdCourse = testCourseService.createCourse(newcourse, "no matter", "no matter", "no matter", "no matter", "no matter"); //create course needs an override method to take a student who is making it to also register
    		if (createdCourse.getCourseID() != newcourse) {
    			Assert.fail();
    		}
    	}
    	catch(Exception e) {
    		Assert.fail();
    	}
    }

    @Then("^a \"([^\"]*)\" message is issued to the user$")
    public void a_something_message_is_issued_to_the_user(String strArg1) throws Throwable {
    	if (!strArg1.equals(error)) {
    		Assert.fail();
    	}
    }

    @Then("^an error message \"([^\"]*)\" is issued to the user$") //should be able to remove one of these
    public void an_error_message_something_is_issued_to_the_user(String strArg1) throws Throwable {
    	if (!strArg1.equals(error)) {
    		Assert.fail();
    	}
    }

    @And("^the user is logged in$")
    public void the_user_is_logged_in() throws Throwable {
        testAccountService.LogIn(testAccount.getEmail(), testAccount.getPassword());
    }


    @And("^the user is registered to the course$")
    public void the_user_is_registered_to_the_course() throws Throwable {
        throw new PendingException(); //no mechanism to allow for this right now, register automatically during course creation
    }


    @And("^the system should register the user to the course$") //remove dup -> also doesnt work for the same reason
    public void the_system_should_register_the_user_to_the_course() throws Throwable {
        throw new PendingException();
    }

    @And("^add the user to GroupUp chat of the course$") //no service mechanism for this unless I didn't see it
    public void add_the_user_to_groupup_chat_of_the_course() throws Throwable {
        throw new PendingException();
    }
    
    
//=========================ID010 Register For A Course============================================//
    
//    Account testAccount = null;
//	Course mycourse = null;
//	AccountService testAccountService = new AccountService();
//	CourseService testCourseService = new CourseService();
//    @Given("^valid email (.+) and password (.+)$")
//    public void valid_email_and_password(String email, String password) throws Throwable {
//    	testAccount = testAccountService.createStudentAccount(new Student(), "No matter", "Ben", email, "McGill", password); //must be valid
//    }

    @Given("^the course (.+) exist$")
    public void the_course_exist(String course) throws Throwable { //duplicate 009
    	testCourseService.createCourse(course, "no matter", "no matter", "no matter", "no matter", "no matter");
    }

    @When("^user (.+) requests register for course (.+)$") 
    public void user_requests_register_for_course(String email, String course) throws Throwable { //we need to decide if course id is a int, if not I need find course by name
    	mycourse = testCourseService.getCourseByCourseID(course).get(0);
        testCourseService.registerStudent((Student) testAccount.getUserRole(), mycourse);
    }

    @Then("^the user will be registered undered the course$")
    public void the_user_will_be_registered_undered_the_course() throws Throwable {
        Student studentAccount = (Student) testAccount.getUserRole();
        if (!studentAccount.getCourses().contains(mycourse)) {
        	Assert.fail("The user did not get registered");
        }
    }

    @Then("^the user will be notified that user is not logged in$")
    public void the_user_will_be_notified_that_user_is_not_logged_in() throws Throwable {
        try {
        	testCourseService.registerStudent((Student) testAccount.getUserRole(), mycourse);
        }
        catch(Exception e) {
        	Assert.assertEquals("Student does not exist", e.getMessage());
        }
    }

    @Then("^the user will be notified that the course does not exist$")
    public void the_user_will_be_notified_that_the_course_does_not_exist() throws Throwable {
        try {
        	testCourseService.registerStudent((Student) testAccount.getUserRole(), mycourse);
        }
        catch(Exception e) {
        	Assert.assertEquals("Course does not exist", e.getMessage());
        }
    }

//    @And("^the user is logged in$")
//    public void the_user_is_logged_in() throws Throwable {
//        testAccountService.LogIn(testAccount.getEmail(), testAccount.getPassword());
//    }

    @And("^the user is not logged in$")
    public void the_user_is_not_logged_in() throws Throwable {
        testAccount = null;
    }

    @And("^the course (.+) doesn't exist$")
    public void the_course_doesnt_exist(String course) throws Throwable {
        mycourse = null; //we know null isn't a registered course
    }
    
    
//=======================ID011 View Enrolled Courses==========================================//
    
//    AccountService testService = new AccountService();
//	CourseService testCourseService = new CourseService();
//	Account testAccount = null;
//	Course mycourse = null;
	Set<Course> requestedCourses = null;
//    @Given("^valid email (.+) and password (.+) $")
//    public void valid_email_and_password(String email, String password) throws Throwable {
//    	testAccount = testService.createStudentAccount(new Student(), "No matter", "Ben", email, "McGill", password); //must be valid
//    }

    @Given("^the user is enrolled in the following courses:$")
    public void the_user_is_enrolled_in_the_following_courses() throws Throwable {
        mycourse = testCourseService.createCourse("Hi", "my", "name", "is", "Ben", "great");
        Student aStudent = (Student) testAccount.getUserRole(); //we know its student since we made it
        testCourseService.registerStudent(aStudent, mycourse);
    }

    @Given("^the user is not enrolled in any courses$")
    public void the_user_is_not_enrolled_in_any_courses() throws Throwable {
    	Student aStudent = (Student) testAccount.getUserRole();
    	for (Course aCourse: aStudent.getCourses()) {
    		testCourseService.unregisterStudent(aStudent, aCourse);
    	}
    }

    @When("^the user requests view enrolled courses$")
    public void the_user_requests_view_enrolled_courses() throws Throwable {
    	Student aStudent = (Student) testAccount.getUserRole();
    	requestedCourses = aStudent.getCourses(); //really should be get enrolled courses, but I don;t know how to get student id
    }

    @Then("^the user will see currently enrolled courses$")
    public void the_user_will_see_currently_enrolled_courses() throws Throwable {
        if (!requestedCourses.contains(mycourse)) {
        	Assert.fail("Course not found");
        }
    }

    @Then("^the system will notify the user \"([^\"]*)\"$")
    public void the_system_will_notify_the_user_something(String strArg1) throws Throwable {
        throw new PendingException(); //here is where the problem from the when rears its head
    }

//    @And("^the user is logged in$")
//    public void the_user_is_logged_in() throws Throwable {
//        testService.LogIn(testAccount.getEmail(), testAccount.getPassword());
//    }
    
    
//=======================ID 012 Unregister From A Course=====================================//
    
//    AccountService testAccountService = new AccountService();
//	CourseService testCourseService = new CourseService();
//	Account testAccount = null;
	Course c = null;
//    @Given("^valid email (.+) and password (.+) $") //find a way to remove duplicates 
//    public void valid_email_and_password(String email, String password) throws Throwable {
//    	testAccount = testAccountService.createStudentAccount(new Student(), "No matter", "Ben", email, "McGill", password); //must be valid, since I am making this
//    }

    @Given("^the user is registered for this (.+)$")
    public void the_user_is_registered_for_this(String course) throws Throwable {
        throw new PendingException(); //ask about get enrolled course, or how to get courses registered for a user??
    }

    @When("^the user requests to deregister from this (.+)$")
    public void the_user_requests_to_deregister_from_this(String course) throws Throwable {
    	throw new PendingException(); //id of course should be string?? 
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

//    @And("^the user is logged in$")
//    public void the_user_is_logged_in() throws Throwable {
//        try{
//        	testAccountService.LogIn(testAccount.getEmail(), testAccount.getPassword());
//        }
//        catch(Exception e) {
//        	Assert.fail("Login was not allowed");
//        }
//    }
    
    
//=============================ID022 View Available Courses===============================//
    
//    CourseService testCourseService = new CourseService();
//	AccountService testAccountService = new AccountService();
	List<Course> allCourses = null;
	List<Course> requestCourses = null;
//	Account testAccount = null;
	//might have to come back to this one...
//    @Given("^valid email (.+) and password (.+) $")
//    public void valid_email_and_password(String email, String password) throws Throwable { //duplicate, ask shudy about how to remove 
//    	testAccount = testAccountService.createStudentAccount(new Student(), "No matter", "Ben", email, "Concordia", password); //must be valid
//    }

    @Given("^the following courses exist:$")
    public void the_following_courses_exist() throws Throwable { //get all the courses for later
        allCourses = testCourseService.getAllCourses(); 
    }

    @When("^the user requests view available courses in \"([^\"]*)\" semester(s) in \"([^\"]*)\" year$")
    public void the_user_requests_view_available_courses_in_something_semesters_in_something_year(String strArg1, String strArg2) throws Throwable {
    	requestCourses = testCourseService.getAllCourses(); 
    }
    

    @Then("^the user will see:$")
    public void the_user_will_see() throws Throwable {
        if (requestCourses == null) {//try to think of more assertive test
        	Assert.fail("The requested courses did not match the examples, should not be null");
        }
    }

//    @And("^the user is logged in$")
//    public void the_user_is_logged_in() throws Throwable {
//        if (testAccount == null) {
//        	Assert.fail("User is not logged in");
//        }
//    }
    
    
    
}
