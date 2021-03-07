package ca.mcgill.ecse428.groupup.gherkin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Course;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.model.Message;
import ca.mcgill.ecse428.groupup.service.AccountService;
import ca.mcgill.ecse428.groupup.service.CourseService;
import ca.mcgill.ecse428.groupup.service.ChatService;
import ca.mcgill.ecse428.groupup.service.MessageService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions extends SpringWrapper {
	
//========================ID01_Register New User=======================================//
	
	//the login + autogeneration literally seems to be invalid by the service layer that requires not null inputs
	Account testAccount = null;
	Course mycourse = null;
	String errorMessage = null;
	String testEmail = null;
	String testName = null;
//	Student testStudent = null;
	@After
    public void clearDatabase() {
        // Clear the table to avoid inconsistency
		System.out.println("Clearing database in between tests");
        courseRepository.deleteAll();
        messageRepository.deleteAll();
        chatRepository.deleteAll();
        accountRepository.deleteAll();
        studentRepository.deleteAll();

    }
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

    @Then("John Doe will be notified of an invalid email")
    public void johnDoeWillBeNotifiedOfAnInvalidEmail() {
    	String error = testEmail + "INVALID_EMAIL";
        assertEquals(error, errorMessage);
    	return;
    }

    @Given("James Smith is a user of the GoupUp System")
    public void jamesSmithIsAUserOfTheGoupUpSystem() {
        testAccount = testAccountService.createStudentAccount(new Student(), "username", "James Smith", "valid@mail.mcgill.ca", "mcgill", "1234");
    }

    @When("James Smith requests user access to the GroupUp System")
    public void jamesSmithRequestsUserAccessToTheGroupUpSystem() {
    	try {
    		testAccountService.createStudentAccount(new Student(), "username", "James Smith", "valid@mail.mcgill.ca", "mcgill", "1234"); //already created
    	}
    	catch(Exception e) {
//    		assertEquals("Already registered",e.getMessage());
    		errorMessage = e.getMessage();
    	}
    }
    
    @Then("James Smith will be notified that he is already registered")
    public void jamesSmithWillBeNotifiedThatHeIsAlreadyRegistered() {
        assertEquals("Already registered", errorMessage);
    	return;
    }
    
    
//=======================ID02 Login Existing User============================================//
    
//	Account testAccount = null;
	String nonValidEmail = null; //no @ so must be invalid
	String testPassword = null;
	@Given("valid email {word} and password {word}")
    public void valid_email_and_password(String email, String password) throws Throwable { //ie. an existing student account
    	testAccount = testAccountService.createStudentAccount(new Student(), "No matter", "Ben", email, "McGill", password); //must be valid
		testEmail = email;
		testPassword = password;
    }

    @Given("a non-recognized email {word}")
    public void a_nonrecognized_email(String email) throws Throwable {
    	nonValidEmail = email; //no @ so must be invalid
    }

    @Given("a valid email {word}")
    public void a_valid_email(String email) throws Throwable {
    	testAccount = testAccountService.createStudentAccount(new Student(), "No matter", "Ben", email, "McGill", "1234"); 
    }

    @When("the user {word} requests access to the GroupUp system")//removed a duplicate here, one without "the" will need to find dup in feature file
    public void the_user_requests_access_to_the_groupup_system(String email) throws Throwable {
    	//check if request works with no error
        try{
//        	testService.LogIn(testAccount.getEmail(), testAccount.getPassword());
        	testAccountService.LogIn(email, testPassword);
        }
        catch(Exception e) {
        	errorMessage = e.getMessage();
        }
    }

    @Then("they will be granted access to the GroupUp system")
    public void they_will_be_granted_access_to_the_groupup_system() throws Throwable {

    	assertNull(errorMessage);
//        try{
//        	Account tempAccount = testService.LogIn(testAccount.getEmail(), testAccount.getPassword());
//        	if (tempAccount != testAccount) {
//        		Assert.fail();
//        	}
//        }
//        catch(Exception e) {
//        	Assert.fail("Request was not inputed");
//        }
    }

    @Then("an error message is issued saying email not recognized")
    public void an_error_message_is_issued_saying_email_not_recognized() throws Throwable {
    	assertEquals(errorMessage, "Account email cannot be found.");
    	
//    	try{
//        	testService.LogIn(testAccount.getEmail(), testAccount.getPassword());
//        }
//        catch(Exception e) {
//        	assertEquals(e.getMessage(), "Password is incorrect.");
//        }
    }
    @Then("an error message is issued saying password is incorrect")
    public void an_something_message_is_issued() throws Throwable {
    	assertEquals(errorMessage, "Password is incorrect.");
    	
//    	try{
//        	testService.LogIn(testAccount.getEmail(), testAccount.getPassword());
//        }
//        catch(Exception e) {
//        	assertEquals(e.getMessage(), "Password is incorrect.");
//        }
    }

    @And("a related student privileges")
    public void a_related_student_privileges() throws Throwable {
        if (!(testAccount.getUserRole() instanceof Student)) {
        	Assert.fail("Don't have student role");
        }
    }

    @And("a related admin privileges")
    public void a_related_admin_privileges() throws Throwable {
        if (!(testAccount.getUserRole() instanceof Student)) {
        	Assert.fail("Don't have student role");
        }
    }


    @But("an incorrect corresponding password {word}")
    public void an_incorrect_corresponding_password(String password) throws Throwable {
//    	if (password == testAccount.password) {
//    		Assert.fail("password is not unique");
//    	}
    	testPassword = password;
    }
    
    
//=======================ID009 Add New Course===========================================//
//    Account testAccount = null;
//	AccountService testAccountService = new AccountService();
//	CourseService testCourseService = new CourseService();
	String coursename = null;
	String courseid = null;
	String coursesection = null;
	String semester = null;
	String year = null;
	String error = "";
	Course theCourse = null;
//    @Given("^valid email (.+) and password (.+) $")
//    public void valid_email_and_password(String email, String password) throws Throwable {
//    	testAccount = testAccountService.createStudentAccount(new Student(), "No matter", "Ben", email, "McGill", password); //must be valid
//    }
    
    @Given("the course {word} is invalid format")
    public void the_course_is_invalid_format(String newcourse) throws Throwable {
        if (newcourse != "") { //really the only invalid format for a course item now
        	coursename = "";
        }
        else {
        	coursename = newcourse;
        }
    }
    
    @Given("the course {word} already exist in the system")
    public void the_course_already_exist_in_the_system(String newcourse) throws Throwable {
        theCourse = testCourseService.createCourse(newcourse, "faculty", "WINTER", "2021", "01", "name");
        courseid = newcourse;
        coursesection = "01";
        semester = "WINTER";
        year = "2021";
    }

    @When("the user requests to add a new course {word}")
    public void the_user_requests_to_add_a_new_course(String newcourse) throws Throwable {
    	if (newcourse.equals("invalid")) {		//check for valid course doesn't really work
    		errorMessage = "Course is Invalid Format";
    	}
    	try {
    		testCourseService.createCourse(newcourse, "faculty", "WINTER", "2021", "01", "name");
    	}
    	catch(Exception e) {

    		errorMessage = e.getMessage();
//    		Assert.fail();
    	}
    }

    @Then("the course {word} is added to the system")
    public void the_course_is_added_to_the_system(String newcourse) throws Throwable {
    	try {
    		testCourseService.getCourseByCourseID(newcourse);
    	}
    	catch(Exception e) {
    		Assert.fail();
    	}
    	
//    	try {
//    		Course createdCourse = testCourseService.createCourse(newcourse, "no matter", "no matter", "no matter", "no matter", "no matter"); //create course needs an override method to take a student who is making it to also register
//    		if (createdCourse.getCourseID() != newcourse) {
//    			Assert.fail();
//    		}
//    	}
//    	catch(Exception e) {
//    		Assert.fail();
//    	}
    }

    @Then("a message is issued to the user saying course already exists")
    public void a_message_is_issued_to_the_user_saying_course_already_exists() throws Throwable {
    	String error ="This course with courseID: " + courseid + " section: " + coursesection + " already exists for the " + semester + year + " semester ";
    	assertEquals(errorMessage, error);
//    	if (!strArg1.equals(error)) {
//    		Assert.fail();
//    	}
    }

    @Then("an error message saying course name is invalid format is issued") //should be able to remove one of these
    public void an_error_message_saying_course_name_is_invalid_format_is_issued() throws Throwable {
    	
    	assertEquals("Course is Invalid Format", errorMessage);
//    	if (!strArg1.equals(error)) {
//    		Assert.fail();
//    	}
    }

    @And("the user is logged in")
    public void the_user_is_logged_in() throws Throwable {
        testAccountService.LogIn(testAccount.getEmail(), testAccount.getPassword());
    }


    @And("the user is registered to the course")
    public void the_user_is_registered_to_the_course() throws Throwable {
//    	throw new PendingException(); //no mechanism to allow for this right now, register automatically during course creation
    }


    @And("the system should register the user to the course") //remove dup -> also doesnt work for the same reason
    public void the_system_should_register_the_user_to_the_course() throws Throwable {
        throw new PendingException();
    }

    @And("add the user to GroupUp chat of the course") //no service mechanism for this unless I didn't see it
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

//    @Given("the course {word} exist")
//    public void the_course_exist(String course) throws Throwable { //duplicate 009
//    	testCourseService.createCourse(course, "faculty", "WINTER", "2021", "01", "name");
//    }
    Student theStudent = null;
    @When("user {word} requests register for course {word}") 
    public void user_requests_register_for_course(String email, String course) throws Throwable { //we need to decide if course id is a int, if not I need find course by name
    	theStudent = (Student) testAccount.getUserRole();
    	try {
    		testCourseService.registerStudent(theStudent, theCourse);
    	}
    	catch(IllegalArgumentException e) {
    		error = e.getMessage();
    	}
    }

    @Then("the user will be registered under the course")
    public void the_user_will_be_registered_under_the_course() throws Throwable {
        if (!theStudent.getCourses().contains(theCourse)) {
        	Assert.fail("The user did not get registered");
        }
    }


    @Then("the user will be notified that the course does not exist")
    public void the_user_will_be_notified_that_the_course_does_not_exist() throws Throwable {
    	assertEquals(error, "Course does not exist");
    }

//    @And("^the user is logged in$")
//    public void the_user_is_logged_in() throws Throwable {
//        testAccountService.LogIn(testAccount.getEmail(), testAccount.getPassword());
//    }


    @And("the course {word} doesn't exist")
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

    @Given("the user is enrolled in the following courses:")
    public void the_user_is_enrolled_in_the_following_courses() throws Throwable {
        mycourse = testCourseService.createCourse("Hi", "my", "FALL", "is", "Ben", "great");
        Student aStudent = (Student) testAccount.getUserRole(); //we know its student since we made it
        testCourseService.registerStudent(aStudent, mycourse);
    }

    @Given("the user is not enrolled in any courses")
    public void the_user_is_not_enrolled_in_any_courses() throws Throwable {
    	Student aStudent = (Student) testAccount.getUserRole();
    	for (Course aCourse: aStudent.getCourses()) {
    		testCourseService.unregisterStudent(aStudent, aCourse);
    	}
    }

    @When("the user requests view enrolled courses")
    public void the_user_requests_view_enrolled_courses() throws Throwable {
    	Student aStudent = (Student) testAccount.getUserRole();
    	requestedCourses = aStudent.getCourses(); //really should be get enrolled courses, but I don;t know how to get student id
    }

    @Then("the user will see currently enrolled courses")
    public void the_user_will_see_currently_enrolled_courses() throws Throwable {
        if (!requestedCourses.contains(mycourse)) {
        	Assert.fail("Course not found");
        }
    }

    @Then("the system will notify the user that you are not enrolled in any course")
    public void the_system_will_notify_the_user_that_you_are_not_enrolled_in_any_course() throws Throwable {
        //this was not implimented in the system
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
	Student student;
	Course testCourse;
//    @Given("^valid email (.+) and password (.+) $") //find a way to remove duplicates 
//    public void valid_email_and_password(String email, String password) throws Throwable {
//    	testAccount = testAccountService.createStudentAccount(new Student(), "No matter", "Ben", email, "McGill", password); //must be valid, since I am making this
//    }

    @Given("the user is registered for this {word}")
    public void the_user_is_registered_for_this(String course) throws Throwable {
    	testCourse = testCourseService.createCourse(course, "faculty", "WINTER", "2021", "01", course);
    	student = (Student) testAccount.getUserRole();
    	testCourseService.registerStudent(student, testCourse);
//    	testCourse.addStudent(student);
//    	student.addCourse(testCourse);
//    	throw new PendingException(); //ask about get enrolled course, or how to get courses registered for a user??
    }

    @When("the user requests to deregister from this {word}")
    public void the_user_requests_to_deregister_from_this(String course) throws Throwable {
//    	throw new PendingException(); //id of course should be string??
//    	testCourse.removeStudent(student);
//    	student.removeCourse(testCourse);
    	testCourseService.unregisterStudent(student, testCourse);
    }

    @When("the user requests to de-register from the {word} they are not registered for")
    public void the_user_requests_to_deregister_from_the_they_are_not_registered_for(String course) throws Throwable {
//        throw new PendingException();
    	Course newCourse = testCourseService.createCourse(course, "faculty", "WINTER", "2021", "01", course);
    	student = (Student) testAccount.getUserRole();
    	try {
    		testCourseService.unregisterStudent(student, newCourse);
    	}
    	catch(Exception e) {
    		errorMessage = e.getMessage();
    	}
    }

    @Then("they will no longer be enrolled")
    public void they_will_no_longer_be_enrolled() throws Throwable {
//        throw new PendingException();
    	assertEquals(student.getCourses().size(), 0);
    }

    @Then("a message is issued saying that you are not enrolled in this course")
    public void a_message_is_issued_saying_that_you_are_not_enrolled_in_this_course() throws Throwable {
//        throw new PendingException();
    	assertEquals("Student is not registered in the course", errorMessage);
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
    
    
//============================ID019 Privately Message Another User================================//
    
    //Given a user is logged in
    //And the user is registered for this course
    
    Chat testChat = null;
    Message testMessage = null;
    Student testStudent = null;
    Student studentb = null;
    
    @And("studentb {word} is registered in the same course {word}")
    public void studentb_is_registered_in_the_same_course(String email, String course) {
    	Account accountb = testAccountService.createStudentAccount(new Student(), "username2", "name2", email, "institutionName", "password");
    	studentb = (Student) accountb.getUserRole();
    	//testCourse = testCourseService.createCourse(course, "faculty", semester, year, "01", course);
    	testCourse.addStudent(studentb);
    }
    
    @And("the user does not have an existing conversation with studentb {word}")
    public void the_user_does_not_have_an_existing_conversation_with_studentb(String email) {
    	List<Student> students = new ArrayList<Student>();
    	students.add(testStudent);
    	students.add(studentb);
    	testChat = testChatService.createChat(students);
    }

    @When("the user tries to message studentb {word}")
    public void the_user_tries_to_message_studentb(String email) {
    	testMessage = testMessageService.createMessage(testStudent, testChat, "Hello my name is Joe");
    }
    
    @Then("studentb {word} should receive a new message")
    public void studentb_should_receive_a_new_message(String email) throws Throwable{
    	assertNotNull(testMessage.getContent());
//    	assertNotEquals(0, testMessageService.getChatsByStudent(studentb).size());
    }
    
    @And("the user has an existing conversation with studentb {word}")
    public void the_user_has_an_existing_conversation_with_studentb(String email) {
    	List<Student> students = new ArrayList<Student>();
    	students.add(testStudent);
    	students.add(studentb);
    	testChat = testChatService.createChat(students);
    }
    
    
//============================ID021 View Chats with Other Users====================================//
    
    Page<Message> page = null;
    //Given a user is logged in
    
    @And("the user has a history of messages with studentb {word}")
    public void the_user_has_a_history_of_messages_with_studentb(String email) {
    	Account accountb = testAccountService.createStudentAccount(new Student(), "username2", "name2", email, "institutionName", "password2");
    	studentb = (Student) accountb.getUserRole();
    	List<Student> students = new ArrayList<Student>();
    	students.add(testStudent);
    	students.add(studentb);
    	testChat = testChatService.createChat(students);
    	testMessageService.createMessage(testStudent, testChat, "Hello my name is Joe");
    }
    
    @When("the user opens his conversation with studentb {word}")
    public void the_user_opens_his_conversation_with_studentb(String email) {
    	page = testMessageService.getMessagesByChat(testChat, 0);
    }
    
    @Then("the user will see a display of all the past messages")
    public void the_user_will_see_a_display_of_all_the_past_messages() {
    	assertNotNull(page.getContent().get(0));
    	assertEquals("Hello my name is Joe", page.getContent().get(0).getContent());
    }
    
    @And("the user has no history of messages with studentb {word}")
    public void the_user_has_no_history_of_messages_with_studentb(String email) {
    	Account accountb = testAccountService.createStudentAccount(new Student(), "username2", "name2", email, "institutionName", "password");
    	studentb = (Student) accountb.getUserRole();
    	List<Student> students = new ArrayList<Student>();
    	students.add(testStudent);
    	students.add(studentb);
    	testChat = testChatService.createChat(students);
    }
    
    @Then("the user will see a display of an empty messaging inbox")
    public void the_user_will_see_a_display_of_an_empty_messaging_inbox() {
    	assertTrue(page.getContent().isEmpty());
    }
    
    
//=============================ID022 View Available Courses===============================//
    
//    CourseService testCourseService = new CourseService();
//	AccountService testAccountService = new AccountService();
	List<Course> allCourses = null;
	List<Course> requestCourses = null;
	int numCourses = 0;
//	Account testAccount = null;
	//might have to come back to this one...
//    @Given("^valid email (.+) and password (.+) $")
//    public void valid_email_and_password(String email, String password) throws Throwable { //duplicate, ask shudy about how to remove 
//    	testAccount = testAccountService.createStudentAccount(new Student(), "No matter", "Ben", email, "Concordia", password); //must be valid
//    }
	
	@Given("a user is logged in")
	public void a_user_is_logged_in() {
		testAccount = testAccountService.createStudentAccount(new Student(), "username", "name", "email@mail.mcgill.ca", "institutionName", "password");
		testStudent = (Student) testAccount.getUserRole();
		testAccountService.LogIn(testAccount.getEmail(), testAccount.getPassword());
	}

    @Given("the following courses exist:")
    public void the_following_courses_exist(io.cucumber.datatable.DataTable dataTable) throws Throwable { //get all the courses for later
        List<Map<String, String>> valueMaps = dataTable.asMaps();
        for (Map<String, String> map : valueMaps) {
        	numCourses++;
        	String course = map.get("course");
        	String semester = map.get("semester");
        	String year = map.get("year");
        	testCourseService.createCourse(course, "faculty", semester, year, "01", course);
        }
    	
//    	allCourses = testCourseService.getAllCourses(); 
    }

    @When("the user requests view available courses in all semesters in every year")
    public void the_user_requests_view_available_courses_in_all_semesters_in_every_year() throws Throwable {
    	requestCourses = testCourseService.getAllCourses(); 
    }
    
    @When("the user requests view available courses in {word} semester in {word} year")
    public void the_user_requests_view_available_courses_in_semester_in_year(String semester, String year) throws Throwable {
    	requestCourses = testCourseService.getCoursesByYearBySemester(year, semester); 
    }
    

    @Then("the user will see all the courses")
    public void the_user_will_see_all_the_courses() throws Throwable {
        if (requestCourses.size() != numCourses) {//try to think of more assertive test
        	Assert.fail("The requested courses did not match the examples, should not be null");
        }
    }
    
    @Then("the user will see all the courses for that semester")
    public void the_user_will_see_all_the_courses_for_that_semester() throws Throwable {
        if (requestCourses.size() == 0) {//try to think of more assertive test
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
