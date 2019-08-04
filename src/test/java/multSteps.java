import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class multSteps {
    int num1;
    int num2;

    int result;
    int expected = 6;

    @Given("two numbers 2 and 4")
    public void two_numbers() throws Exception {
        num1 = 2;
        num2 = 3;
    }

    @When("I multiply the two numbers")
    public void i_mult_the_two_numbers() throws Exception {
        mult mult = new mult(2,3);
        multTwoNumbers multTwoNumbers = new multTwoNumbers();

        result = multTwoNumbers.multTwoValues(mult);

    }

    @Then("I should get 8")
    public void i_should_get() throws Exception {

        try {
            assertEquals(expected,result);
            System.out.println("Pass");
        } catch (AssertionError e) {
            System.out.println("Failed");
            throw e;
        }

    }
}
