import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class addSteps {


    int num1;
    int num2;

    int result;
    int expected = 5;

    @Given("two numbers 2 and 3")
    public void two_numbers_and() throws Exception {
       num1 = 2;
       num2 = 3;
    }

    @When("I add the two numbers")
    public void i_add_the_two_numbers() throws Exception {
        Add add = new Add(2,3);
        addTwoNumbers addTwoNumbers = new addTwoNumbers();

       result = addTwoNumbers.addValues(add);

    }

    @Then("I should get 5")
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
