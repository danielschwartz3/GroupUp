package ca.mcgill.ecse428.groupup.gherkin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import org.junit.Assert;

import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Course;
import ca.mcgill.ecse428.groupup.model.Student;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ChangeUserInfoStepDefinition extends SpringWrapper {

    Account testAccount = null;
	Course mycourse = null;
	String errorMessage = null;
	String testEmail = null;
	String testName = null;
    String testInstitution = null;
    String testUserName = null;

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

    /**----------NORMAL FLOW ----------*/
    @Given("user with username {string}, email {string}, name {string}, institution {string}, password {string}")
    public void user_with_oldName_oldEmail_oldName_oldInstitution_old_password(String old_username, String old_email, String old_name, String old_institution, String old_password){
        testAccount = testAccountService.createStudentAccount(new Student(),old_username, old_name, old_email,old_institution, old_password);
        testEmail = old_email;
        testInstitution = old_institution;
        testName = old_name;
        testInstitution = old_institution;
        testUserName = old_username;
        return; 
    }
    
    @When("the user with email {string} tries to change their information to username {string}, email {string}, name {string}, institution {string}, password {string}")
    public void studentUser_tries_to_change_their_information(String old_email, String new_userName, String new_email, String new_name, String new_institution, String new_password){
        try {
            testAccount = testAccountService.changeUserInformation(old_email, new_userName, new_name, new_email, testInstitution);
            assertNotNull(testAccount);
            testUserName = new_userName;
        }
        catch(Exception e) {
            Assert.fail();
        }
        return;
    }
    
    @Then("the user with email {string} will have information username {string}, email {string}, name {string}, institution {string}, password {string}")
    public void user_information_is_changed(String new_email, String new_username, String email, String new_name, String new_institution, String new_password){
        testAccount = testAccountService.getAccountByID(testEmail);
        assertNotNull(testAccount);
        assertEquals(testEmail, testAccount.getEmail());
        assertEquals(testInstitution, testAccount.getInstitution());
        assertEquals(testUserName, testAccount.getUserName());
        return;
    }

    /**-----------ERROR FLOW  #1----------*/

    @Given("user's username {string}, email {string}, name {string}, institution {string}, password {string}")
    public void user_s_username_email_name_institution_password(String old_username, String old_email, String old_name, String old_institution, String old_password){
        testAccount = testAccountService.createStudentAccount(new Student(),old_username, old_name, old_email,old_institution, old_password);
        testEmail = old_email;
        testInstitution = old_institution;
        testName = old_name;
        testInstitution = old_institution;
        testUserName = old_username;
        return; 
    }
    
    @When("the user tries to change their information to username {string}, email {string}, name {string}, institution {string}, password {string}")
    public void student_User_tries_to_change_their_information(String username, String email, String name, String institution, String password){
        try {
            testAccount = testAccountService.changeUserInformation(testEmail, username, name, email, institution);
        }
        catch(Exception e) {
            errorMessage = e.getMessage();
        }
        return;
    }
    
    @Then("the user will receive a message, You cannot change your email, please enter valid information\"")
    public void user_cannot_change_email(){
        String error = "You cannot change your email, please enter valid information";
        assertEquals(error, errorMessage);
        return;
    }

    @Then("the user will have information username {string}, email {string}, name {string}, institution {string}, password {string}")
    public void user_retains_old_information(String username, String email, String new_name, String new_institution, String new_password){
        testAccount = testAccountService.getAccountByID(email);
        assertNotNull(testAccount);
        assertEquals(testEmail, testAccount.getEmail());
        assertEquals(testInstitution, testAccount.getInstitution());
        assertEquals(testUserName, testAccount.getUserName());
        return;
    }

    /**--------ERROR FLOW #2------------*/

    @Given("user with the username {string}, email {string}, name {string}, institution {string}, password {string}")
    public void user_with_the_username_email_name_institution_password(String old_username, String old_email, String old_name, String old_institution, String old_password){
        testAccount = testAccountService.createStudentAccount(new Student(),old_username, old_name, old_email,old_institution, old_password);
        testEmail = old_email;
        testInstitution = old_institution;
        testName = old_name;
        testInstitution = old_institution;
        testUserName = old_username;
        return; 
    }
    
    @When("the user tries to change their username {string}, email {string}, name {string}, institution {string}, password {string}")
    public void user_tries_to_change_their_information(String username, String email, String name, String institution, String password){
        try {
            testAccount = testAccountService.changeUserInformation(testEmail, username, name, email, institution);
        }
        catch(Exception e) {
            errorMessage = e.getMessage();
        }
        return;
    }
    
    @Then("the user will receive a message, You cannot change your institution as it is linked to your email, please enter valid information\"")
    public void user_cannot_change_institution(){
        String error = "You cannot change your institution as it is linked to your email, please enter valid information";
        assertEquals(error, errorMessage);
        return;
    }

    @Then("the user will have username {string}, email {string}, name {string}, institution {string}, password {string}")
    public void user_will_retain_old_information(String username, String email, String new_name, String new_institution, String new_password){
        testAccount = testAccountService.getAccountByID(email);
        assertNotNull(testAccount);
        assertEquals(testEmail, testAccount.getEmail());
        assertEquals(testInstitution, testAccount.getInstitution());
        assertEquals(testUserName, testAccount.getUserName());
        return;
    }
}
