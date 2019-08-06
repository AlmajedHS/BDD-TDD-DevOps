import org.junit.Test;

import static org.junit.Assert.*;

public class addTwoNumbersTest {


    @Test
    public void Add_Two_Numbers_Test(){
        Add add = new Add(2,3);
        addTwoNumbers addTwoNumbers = new addTwoNumbers();

       int result = addTwoNumbers.addValues(add);

        try {
            assertEquals(5,result);
            System.out.println("Pass");
        } catch (AssertionError e) {
            System.out.println("Failed");
            throw e;
        }
    }

}