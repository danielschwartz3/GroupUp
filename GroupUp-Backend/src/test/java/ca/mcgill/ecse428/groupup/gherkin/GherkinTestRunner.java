package ca.mcgill.ecse428.groupup.gherkin;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//Change the Feature to test
@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/ID012_Unregister_From_A_Course.feature"}, glue= {"ca.mcgill.ecse428.groupup.gherkin"})
public class GherkinTestRunner {
	
}
