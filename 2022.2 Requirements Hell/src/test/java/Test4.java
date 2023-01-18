import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Test4 {
    @Test
    public void test4() {
        if (Test5.isTest5Success() || Test6.isTest6Success()) {
            return;
        }
        if (!Test3.isTest3Success()) {
            fail("First make sure Test3 has passed");
        }

        actualTest4();
    }

    private void actualTest4() {
        List<String> testNames = asList("Sarah", "christina", "Emma", "Kate", "Olivia", "Andrea", "Laura");

        List<String> actualList = new SuperAwesomeNameService().doTheThings(testNames);

        List<String> expectedList = asList("Laura", "Olivia", "christina", "Emma", "Sarah");
        assertEquals(expectedList, actualList);

        System.out.println("This didn't do exactly what I expected. Forget what I said about the 'e' and consecutive vowels. \n" +
                "Instead reverse a name if the previous name had an 'a' in it, so I get some less common names. \n" +
                "We're looking for something unique here!");
    }

    protected static boolean isTest4Success() {
        Test4 test4 = new Test4();
        try {
            test4.actualTest4();
        } catch (java.lang.AssertionError error) {
            return false;
        }
        return true;
    }
}
