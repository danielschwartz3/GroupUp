package ca.mcgill.ecse428.groupup.gherkin;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterNewUserStepDefinitions {
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

    @Given("John Doe uses email INVALID_EMAIL to request to be a registered user")
    public void johnDoeUsesEmailINVALID_EMAILToRequestToBeARegisteredUser() {
        return;
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
        return;
    }

    @When("James Smith requests user access to the GroupUp System")
    public void jamesSmithRequestsUserAccessToTheGroupUpSystem() {
        return;
    }
}
