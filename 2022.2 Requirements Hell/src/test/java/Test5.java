import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Test5 {
    @Test
    public void test5() {
        if (Test6.isTest6Success()) {
            return;
        }
        if (!Test4.isTest4Success() && !isTest5Success()) {
            fail("First make sure Test4 has passed");
        }

        actualTest5();
    }

    private void actualTest5() {
        List<String> testNames = asList("Liz", "Sarah", "christina", "Eevee", "Kate", "Olivia");

        List<String> actualList = new SuperAwesomeNameService().doTheThings(testNames);

        List<String> expectedList = asList("christina", "eeveE", "Kate", "ziL", "Olivia", "haraS");
        assertEquals(expectedList, actualList);
        System.out.println("That looks a bit weird with the capitals at the end. Can you make sure the reversed names start \n" +
                "again with a capital instead?");
    }

    protected static boolean isTest5Success() {
        Test5 test5 = new Test5();
        try {
            test5.actualTest5();
        } catch (java.lang.AssertionError error) {
            return false;
        }
        return true;
    }
}
