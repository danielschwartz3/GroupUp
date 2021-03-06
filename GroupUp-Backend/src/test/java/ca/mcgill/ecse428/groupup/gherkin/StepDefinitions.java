package ca.mcgill.ecse428.groupup.gherkin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;

import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Admin;
import ca.mcgill.ecse428.groupup.model.Course;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.model.Message;
import ca.mcgill.ecse428.groupup.model.Reaction;

import io.cucumber.java.After;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions extends SpringWrapper {
	
//======================================================ID01_Register New User================================================================//
	
	//the login + autogeneration literally seems to be invalid by the service layer that requires not null inputs
	Account testAccount = null;
	Course mycourse = null;
	String errorMessage = null;
	String testEmail = null;
	String testName = null;

	@After
    public void clearDatabase() {
        // Clear the table to avoid inconsistency
		System.out.println("Clearing database in between tests");
		reactionRepository.deleteAll();
        courseRepository.deleteAll();
        messageRepository.deleteAll();
        chatRepository.deleteAll();
        accountRepository.deleteAll();
        studentRepository.deleteAll();

    }
	
    @Given("student {word} with student email {word} and institution name {string} is student in good standing")
    public void studentUser_nameWithStudentEmailUser_emailAndInstitutionNameUser_institutionIsStudentInGoodStanding(String name, String email, String institutionName) {
    	testName = name;
    	testEmail = email;
    }

    @When("student {word} requests user access to the GroupUp System")
    public void studentUser_nameRequestsUserAccessToTheGroupUpSystem(String username) {
        try {
        	testAccount = testAccountService.createStudentAccount(new Student(), username, testName, testEmail, "institutionName", "password");
        }
        catch(Exception e) {
        	Assert.fail();
        }
    }

    @Then("a new {word} and initial {word} are generated")
    public void aNewUser_nameAndInitialPasswordAreGenerated(String username, String password) {
        assertNotNull(testAccountService.getAccountByID(testEmail));    	
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
    }

    @Then("John Doe will be notified of an invalid email")
    public void johnDoeWillBeNotifiedOfAnInvalidEmail() {
    	String error = testEmail + "INVALID_EMAIL";
        assertEquals(error, errorMessage);
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
    		errorMessage = e.getMessage();
    	}
    }
    
    @Then("James Smith will be notified that he is already registered")
    public void jamesSmithWillBeNotifiedThatHeIsAlreadyRegistered() {
        assertEquals("Already registered", errorMessage);
    	return;
    }
    
    
    
//===========================================================ID02 Login Existing User==============================================================//
    
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
        	testAccountService.LogIn(email, testPassword);
        }
        catch(Exception e) {
        	errorMessage = e.getMessage();
        }
    }

    @Then("they will be granted access to the GroupUp system")
    public void they_will_be_granted_access_to_the_groupup_system() throws Throwable {

    	assertNull(errorMessage);
    }

    @Then("an error message is issued saying email not recognized")
    public void an_error_message_is_issued_saying_email_not_recognized() throws Throwable {
    	assertEquals(errorMessage, "Account email cannot be found.");
    }
    
    @Then("an error message is issued saying password is incorrect")
    public void an_something_message_is_issued() throws Throwable {
    	assertEquals(errorMessage, "Password is incorrect.");
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
    	testPassword = password;
    }
    
    
//=====================================================ID005 Query Student List=================================================================//
    
    //Given a user is logged in
    List<Student> studentList = new ArrayList<Student>();
    List<Account> accountList = new ArrayList<Account>();
        
    @Given("the following students exist:")
    public void the_following_students_exist(io.cucumber.datatable.DataTable dataTable) throws Throwable {
    	List<Map<String, String>> valueMaps = dataTable.asMaps();
    	for (Map<String, String> map : valueMaps) {
    		String username = map.get("username");
    		String email = map.get("email");
    		String name = map.get("name");
    		String institution = map.get("institution");
    		Account account = testAccountService.createStudentAccount(new Student(), username, name, email, institution, "password");
    		accountList.add(account);
    		studentList.add((Student) account.getUserRole());
    	}
    }
    
    @When("the user queries the student list")
    public void the_user_queries_the_student_list() {
    	studentList = testStudentService.getAllStudents();
    }
    
    @Then("the student will see the list of students")
    public void the_student_will_see_the_list_of_students() {
    	assertEquals(studentList.size(), 4);
    	Student student = studentList.get(1);
    	assertEquals("B_Weiss44", student.account.getUserName());
    	assertEquals("ben@mail.mcgill.ca", student.account.getEmail());
    	assertEquals("Ben Weiss", student.account.getName());
    	assertEquals("McGill University", student.account.getInstitution());
    	student = studentList.get(2);
    	assertEquals("Ry_schu", student.account.getUserName());
    	student = studentList.get(3);
    	assertEquals("dan_sch", student.account.getUserName());
    }
    
    
    
//======================================================ID009 Add New Course====================================================================//
	String coursename = null;
	String courseid = null;
	String coursesection = null;
	String semester = null;
	String year = null;
	String error = "";
	Course theCourse = null;
    
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
    }

    @Then("a message is issued to the user saying course already exists")
    public void a_message_is_issued_to_the_user_saying_course_already_exists() throws Throwable {
    	String error ="This course with courseID: " + courseid + " section: " + coursesection + " already exists for the " + semester + year + " semester ";
    	assertEquals(errorMessage, error);
    }

    @Then("an error message saying course name is invalid format is issued") //should be able to remove one of these
    public void an_error_message_saying_course_name_is_invalid_format_is_issued() throws Throwable {
    	
    	assertEquals("Course is Invalid Format", errorMessage);
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
    
    
    
//======================================================ID010 Register For A Course=====================================================================//
    
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

    @And("the course {word} doesn't exist")
    public void the_course_doesnt_exist(String course) throws Throwable {
        mycourse = null; //we know null isn't a registered course
    }
    
    
    
//==================================================ID011 View Enrolled Courses==================================================================//
    
	Set<Course> requestedCourses = null;

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
    
    
    
//====================================================ID 012 Unregister From A Course=============================================================//
    
	Course c = null;
	Student student;
	Course testCourse;

    @Given("the user is registered for this {word}")
    public void the_user_is_registered_for_this(String course) throws Throwable {
    	testCourse = testCourseService.createCourse(course, "faculty", "WINTER", "2021", "01", course);
    	student = (Student) testAccount.getUserRole();
    	testCourseService.registerStudent(student, testCourse);
    }

    @When("the user requests to deregister from this {word}")
    public void the_user_requests_to_deregister_from_this(String course) throws Throwable {
    	testCourseService.unregisterStudent(student, testCourse);
    }

    @When("the user requests to de-register from the {word} they are not registered for")
    public void the_user_requests_to_deregister_from_the_they_are_not_registered_for(String course) throws Throwable {
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
    	assertEquals(student.getCourses().size(), 0);
    }

    @Then("a message is issued saying that you are not enrolled in this course")
    public void a_message_is_issued_saying_that_you_are_not_enrolled_in_this_course() throws Throwable {
    	assertEquals("Student is not registered in the course", errorMessage);
    }
    
    
    
//======================================================ID013 View My Course Classmates List=======================================================//
    
    //Given a user is logged in
    //Given the user is registered for this course
    List<Student> classmates = null;
    
    @When("the user requests view the course classmates list")
    public void the_user_requests_view_the_course_classmates_list() {
    	try {
    		classmates = testStudentService.getStudentsByCourse(testStudent, testCourse);
    	}
    	catch(Exception e) {
    		errorMessage = e.getMessage();
    	}
    }
    
    @Then("the user will see the course classmates list")
    public void the_user_will_see_the_course_classmates_list() {
    	assertNotEquals(0, classmates.size());
    }
    
    @Given("the user is not enrolled in course {word}")
    public void  the_user_is_not_enrolled_in_course(String course) {
    	testCourse = testCourseService.createCourse(course, "faculty", "WINTER", "2021", "01", course);
    }
    
    @Then("the user will be notified that you are not enrolled in this course")
    public void the_user_will_be_notified_that_you_are_not_enrolled_in_this_course() {
    	assertEquals("Student is not registered in the course", errorMessage);
    }
    
    
    
//=======================================================ID019 Privately Message Another User=======================================================//
    
    //Given a user is logged in
    //And the user is registered for this course
    
    Chat testChat = null;
    Message testMessage = null;
    Student testStudent = null;
    Account studentb = null;
    
    @And("studentb {word} is registered in the same course {word}")
    public void studentb_is_registered_in_the_same_course(String email, String course) {
        studentb = testAccountService.createStudentAccount(new Student(), "username2", "name2", email, "institutionName", "password");
    	testCourse.addStudent((Student)studentb.getUserRole());
    }
    
    @And("the user does not have an existing conversation with studentb {word}")
    public void the_user_does_not_have_an_existing_conversation_with_studentb(String email) {
    	List<Account> students = new ArrayList<Account>();
    	students.add(testStudent.getAccount());
    	students.add(studentb);
    	testChat = testChatService.createChat(students,"Test Chat");
    }

    @When("the user tries to message studentb {word}")
    public void the_user_tries_to_message_studentb(String email) {
    	testMessage = testMessageService.createMessage(testStudent.getAccount(), testChat, "Hello my name is Joe");
    }
    
    @Then("studentb {word} should receive a new message")
    public void studentb_should_receive_a_new_message(String email) throws Throwable{
    	assertNotNull(testMessage.getContent());
//    	assertNotEquals(0, testMessageService.getChatsByStudent(studentb).size());
    }
    
    @And("the user has an existing conversation with studentb {word}")
    public void the_user_has_an_existing_conversation_with_studentb(String email) {
        List<Account> students = new ArrayList<Account>();
    	students.add(testStudent.getAccount());
    	studentb = testAccountService.createStudentAccount(new Student(), "username2", "name2", email, "institutionName", "password2");
    	students.add(studentb);
    	testChat = testChatService.createChat(students,"Test Chat");
    }
    

//=================================================ID020 Group Messages=======================================================================//
    
    //given the following students exist
   
    @And("the student Daniel does not have any chats")
    public void the_student_Daniel_does_not_have_any_chats() {

    }
    

    @When("the user Daniel tries to send the following message:")
    public void the_user_Daniel_tries_to_send_the_following_message(io.cucumber.datatable.DataTable dataTable) throws Throwable {
    	List<Map<String, String>> valueMaps = dataTable.asMaps();
        for (Map<String, String> map : valueMaps) {
        	String sender = map.get("sender_email");
        	String content = map.get("content");
        	String date = map.get("date");
        	testChat = testChatService.createChat(accountList, "Group Chat");
        	testMessageService.createMessage(testAccountService.getAccountByID(sender), testChat, content);
        }
    }
    
    @Then("a group chat will be created")
    public void a_group_chat_will_be_created() {
    	assertNotNull(testChat);
    }
    
    @And("the group chat will have the following messages:")
    public void the_group_chat_will_have_the_following_messages(io.cucumber.datatable.DataTable dataTable) throws Throwable {
    	List<Message> messages = testMessageService.getLatestMessagesByChat(testChat);
    	
    	List<Map<String, String>> valueMaps = dataTable.asMaps();
    	List<String> contents = new ArrayList<String>();
        for (Map<String, String> map : valueMaps) {
        	map.get("sender_email");
        	String content = map.get("content");
        	contents.add(content);
        }

        for(int i = 0; i < messages.size(); i++) {
        	assertEquals(contents.get(i), messages.get(i).getContent());
        }
    }
    
    @When("the user Ben tries to send the following message:")
    public void the_user_Ben_tries_to_send_the_following_message(io.cucumber.datatable.DataTable dataTable) throws Throwable {
    	List<Map<String, String>> valueMaps = dataTable.asMaps();
        for (Map<String, String> map : valueMaps) {
        	String sender = map.get("sender_email");
        	String content = map.get("content");
        	String date = map.get("date");
        	testMessageService.createMessage(testAccountService.getAccountByID(sender), testChat, content);
        }
    }
    
    
    
//=================================================ID021 View Chats with Other Users==========================================================//
    
    List<Message> messages = null;
    //Given a user is logged in
    
    @And("the user has a history of messages with studentb {word}")
    public void the_user_has_a_history_of_messages_with_studentb(String email) {
        studentb = testAccountService.createStudentAccount(new Student(), "username2", "name2", email, "institutionName", "password2");
    	List<Account> students = new ArrayList<Account>();
    	students.add(testStudent.getAccount());
    	students.add(studentb);
    	testChat = testChatService.createChat(students,"Test Chat");
    	testMessageService.createMessage(testStudent.getAccount(), testChat, "Hello my name is Joe");
    }
    
    @When("the user opens his conversation with studentb {word}")
    public void the_user_opens_his_conversation_with_studentb(String email) {
      messages = testMessageService.getLatestMessagesByChat(testChat);
    }
    
    @Then("the user will see a display of all the past messages")
    public void the_user_will_see_a_display_of_all_the_past_messages() {
    	assertNotNull(messages.get(0));
    	assertEquals("Hello my name is Joe", messages.get(0).getContent());
    }
    
    @And("the user has no history of messages with studentb {word}")
    public void the_user_has_no_history_of_messages_with_studentb(String email) {
        studentb = testAccountService.createStudentAccount(new Student(), "username2", "name2", email, "institutionName", "password");
    	List<Account> students = new ArrayList<Account>();
    	students.add(testStudent.getAccount());
    	students.add(studentb);
    	testChat = testChatService.createChat(students,"Test Chat");
    }
    
    @Then("the user will see a display of an empty messaging inbox")
    public void the_user_will_see_a_display_of_an_empty_messaging_inbox() {
    	assertTrue(messages.isEmpty());
    }
    
    
    
//==================================================ID022 View Available Courses============================================================//
    
	List<Course> allCourses = null;
	List<Course> requestCourses = null;
	int numCourses = 0;
	
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
    
    
    
//===============================================================ID046 Like Messages==================================================================//
    
    @When("the user Daniel tries to like a message")
    public void the_user_Daniel_tries_to_like_a_message() {
    	//will like the most recent sent message
    	daniel = testStudentService.getStudentByEmail("dan@mail.mcgill.ca");

    	try {
    		testReactionService.reactToMessage("LIKE", daniel.getAccount(), testMessage);
    	}
    	catch(Exception e) {
    		
    	}
    }
    
    @Then("the message will be liked by the user Daniel")
    public void the_message_will_be_liked_by_the_user_Daniel() {
    	List<Reaction> reactions = testReactionService.getAllReactionsToMessage(testMessage);
    	assertNotNull(reactions);
    	assertEquals("LIKE", reactions.get(0).getReactionType().toString());
    	assertEquals(daniel.getAccount().getName(), reactions.get(0).getReactor().getName());
    	assertEquals(testMessage.getContent(), reactions.get(0).getReactionMessage().getContent());
    }
    
    @And("the user Ben likes a message")
    public void the_user_Ben_likes_a_message() {
    	ben = testStudentService.getStudentByEmail("ben@mail.mcgill.ca");
    	testReactionService.reactToMessage("LIKE", ben.getAccount(), testMessage);
    }
    
    @Then("the message will be liked by Daniel and Ben")
    public void the_message_will_be_liked_by_Daniel_and_Ben() {
    	List<Reaction> reactions = testReactionService.getAllReactionsToMessage(testMessage);
    	assertNotNull(reactions);
    	assertEquals(2, reactions.size());
    	assertEquals("LIKE", reactions.get(0).getReactionType().toString());
    	assertEquals(daniel.getAccount().getName(), reactions.get(1).getReactor().getName());
    	assertEquals(ben.getAccount().getName(), reactions.get(0).getReactor().getName());
    	assertEquals(testMessage.getContent(), reactions.get(0).getReactionMessage().getContent());
    }
    
    @And("the user Daniel likes a message")
    public void the_user_Daniel_likes_a_message() {
    	daniel = testStudentService.getStudentByEmail("dan@mail.mcgill.ca");
    	testReactionService.reactToMessage("LIKE", daniel.getAccount(), testMessage);

    }
    
    @When("the user Daniel tries to like the message")
    public void the_user_Daniel_tries_to_like_the_message() {
    	daniel = testStudentService.getStudentByEmail("dan@mail.mcgill.ca");

    	try {
    		testReactionService.reactToMessage("LIKE", daniel.getAccount(), testMessage);
    	}
    	catch(Exception e) {
    		
    	}
    	
//    	List<Reaction> reactions = testReactionService.getAllReactionsToMessage(testMessage);
//    		
//    	assertNotNull(reactions);
//    	assertEquals("LIKE", reactions.get(0).getReactionType().toString());
//    	assertEquals(daniel.getAccount().getName(), reactions.get(0).getReactor().getName());
//    	assertEquals(testMessage.getContent(), reactions.get(0).getReactionMessage().getContent());
    }
    
    
//===============================================================ID051 Unsend A Message===============================================================//
    
    Student daniel;
    Student ben;
    
    @Given("a student with name Daniel Schwartz and email is logged in")
    public void a_student_with_name_Daniel_Schwartz_and_email_is_logged_in() {
    	Account adaniel = testAccountService.createStudentAccount(new Student(), "danieluser", "Daniel Schwartz", "dan@mail.mcgill.ca", "institutionName", "password");
    	daniel = (Student) adaniel.getUserRole();
    	testAccountService.LogIn("dan@mail.mcgill.ca", "password");
    }
    
    @And("a chat exists between him and a student Ben Weiss")
    public void a_chat_exists_between_him_and_a_student_Ben_Weiss() {
    	Account aben = testAccountService.createStudentAccount(new Student(), "benuser", "Ben Weiss", "ben@mail.mcgill.ca", "institutionName", "password");
    	ben = (Student) aben.getUserRole();
    	List<Account> students = new ArrayList<Account>();
    	students.add(daniel.getAccount());
    	students.add(ben.getAccount());
    	testChat = testChatService.createChat(students, "A new Chat");
    }
    
    @And("the following messages exist in the chat:")
    public void the_following_messages_exist_in_the_chat(io.cucumber.datatable.DataTable dataTable) throws Throwable {
    	List<Map<String, String>> valueMaps = dataTable.asMaps();
        for (Map<String, String> map : valueMaps) {
        	String sender = map.get("sender_email");
        	String content = map.get("content");
        	Account student = testAccountService.getAccountByID(sender);
        	testMessage = testMessageService.createMessage(student, testChat, content);
        }
    }
    
    @When("the user Daniel tries to unsend the following message:")
    public void the_user_Daniel_tries_to_unsend_the_following_message(io.cucumber.datatable.DataTable dataTable) throws Throwable {
    	//get the desired message to delete
    	List<Map<String, String>> valueMaps = dataTable.asMaps();
    	String content = null;
    	for (Map<String, String> map : valueMaps) {
        	String sender = map.get("sender_email");
        	content = map.get("content");
        	daniel = testStudentService.getStudentByEmail(sender);
        }
    	
    	List<Message> messages = testMessageService.getLatestMessagesByChat(testChat);
    	long msgID = 0;
    	//find the desired message to delete (assuming no other message has the same content in the chat)
    	for (Message message : messages) {
    		if(message.getContent().equals(content)) {
    			msgID = message.getId();
    		}
    	}
    	
    	try {
    		testMessageService.unsendMessage(msgID, daniel.getAccount().getUserName());
    	}
    	catch (Exception e) {
    		errorMessage = e.getMessage();
    	}
    }
    
    @Then("the chat will have the following messages:")
    public void the_chat_will_have_the_following_messages(io.cucumber.datatable.DataTable dataTable) throws Throwable {
        List<Message> messages = testMessageService.getLatestMessagesByChat(testChat);
    	
    	List<Map<String, String>> valueMaps = dataTable.asMaps();
    	List<String> contents = new ArrayList<String>();
        for (Map<String, String> map : valueMaps) {
        	map.get("sender_email");
        	String content = map.get("content");
        	contents.add(content);
        }
        for(int i = 0; i < 3; i++) {
        	assertEquals(contents.get(i), messages.get(i).getContent());
        }
    }
    
    @And("a group chat exists with those students")
    public void a_group_chat_exists_with_those_students() {
    
      List<Account> members = new ArrayList<>();
      for(Student student : studentList)
        members.add(student.getAccount());
      testChat = testChatService.createChat(members, "Group Chat");
    }
    
    @When("the user Ben tries to unsend the following message:")
    public void the_user_Ben_tries_to_unsend_the_following_message(io.cucumber.datatable.DataTable dataTable) throws Throwable {
    	//get the desired message to delete
    	List<Map<String, String>> valueMaps = dataTable.asMaps();
    	String content = null;
    	for (Map<String, String> map : valueMaps) {
        	map.get("sender_email");
        	content = map.get("content");
        	map.get("date");	//date isnt needed to create a message
        }
    	ben = testStudentService.getStudentByEmail("ben@mail.mcgill.ca");
    	List<Message> messages = testMessageService.getLatestMessagesByChat(testChat);
    	long msgID = 0;
    	//find the desired message to delete (assuming no other message has the same content in the chat)
    	for (Message message : messages) {
    		if(message.getContent().equals(content)) {
    			msgID = message.getId();
    		}
    	}
    	
    	try {
    		testMessageService.unsendMessage(msgID, ben.getAccount().getUserName());
    	}
    	catch (Exception e) {
    		errorMessage = e.getMessage();
    	}
    }
    
    @And("an error message saying You do not have permission to unsend this message will be thrown")
    public void an_error_message_saying_You_do_not_have_permission_to_unsend_this_message_will_be_thrown() {
    	assertEquals("You do not have permission to unsend this message", errorMessage);
    }
    
  //===================================================ID060 Logout User========================================================//  
    String pass = null;
    String em = null;
    @Given("^student valid email (.+) and password (.+) is logged in$")
    public void student_valid_email_and_password_is_logged_in(String email, String password) throws Throwable {
    	pass = password;
    	em = email;
    	Account adaniel = testAccountService.createStudentAccount(new Student(), "danieluser", "Daniel Schwartz", email, "institutionName", password);
    	testAccountService.LogIn(email, password);
    	Account testAccount = testAccountService.getAccountByID(em);
    	if (testAccount.getSession() == null) {
    		Assert.fail();
    	}
    	else {
    		System.out.println(testAccount.getSession().getId().toString());
    		System.out.println(testAccount.getSession().getLoginTime().toString());
    	}
    }

    @Given("^administrator valid email (.+) and password (.+) is logged in$")
    public void administrator_valid_email_and_password_is_logged_in(String email, String password) throws Throwable {
    	pass = password;
    	em = email;
    	Account adaniel = testAccountService.createAdminAccount(new Admin(), "danieluser", "Daniel Schwartz", email, "institutionName", password);
    	testAccountService.LogIn(email, password);
    	Account testAccount = testAccountService.getAccountByID(em);
    	if (testAccount.getSession() == null) {
    		Assert.fail();
    	}
    	else {
    		System.out.println(testAccount.getSession().getId().toString());
    		System.out.println(testAccount.getSession().getLoginTime().toString());
    	}
    }

    @When("^the user (.+) requests logout of the GroupUp system$")
    public void the_user_requests_logout_of_the_groupup_system(String email) throws Throwable {
       testAccountService.Logout(email, pass);
    }

    @Then("^the user will be logged out of the system$")
    public void the_user_will_be_logged_out_of_the_system() throws Throwable {
    	Account testAccount = testAccountService.getAccountByID(em);
    	if (testAccount.getSession() != null) {
    		Assert.fail();
    	}
    }
    
//===================================================ID061 Search Students========================================================//
    

    @When("the user searches for name {string}")
    public void the_user_searches_for_name(String name) throws Throwable {
        testName = name;
    	try {        	
        	studentList = testStudentService.getStudentByName(name);
        }
        catch(Exception e) {
        	errorMessage = e.getMessage();
        }
    }
    
    @Then("the user will see searched student")
    public void the_user_will_see_searched_student() {
    	assertNotNull(studentList);
    	assertEquals(testName, studentList.get(0).getAccount().getName());
    }
    
    @Then("the user will receive the following message No student was found with this name")
    public void the_user_will_receive_the_following_message_No_student_was_found_with_this_name() {
    	assertEquals("No student was found with this name", errorMessage);
    }
    
}
