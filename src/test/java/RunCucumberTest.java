import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


import org.junit.runner.RunWith;


//if you want to run a specific feature, write the path to that feature
//make sure steps in feature file are not the same as other feature files
//step class and feature file should have the exact spelling for features/steps.
//you can define multiple scenarios in one feature file

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"},features = "src/test/resources") //plugin is used to specify the output format
public class RunCucumberTest{
}