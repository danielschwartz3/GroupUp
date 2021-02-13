package ca.mcgill.ecse428.groupup.gherkin;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/ID01_Register_New_User.feature"}, glue= {"ca.mcgill.ecse428.groupup.gherkin"})
// Change the Feature to test
public class GherkinTestRunner {
	
}
