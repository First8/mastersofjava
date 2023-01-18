import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Test6 {
    @Test
    public void test6() {
        if (!Test5.isTest5Success() && !isTest6Success()) {
            fail("First make sure Test5 has passed");
        }
        actualTest6();
    }

    private void actualTest6() {
        List<String> testNames = asList("Liz", "Sarah", "christina", "Eevee", "Kate", "Olivia");

        List<String> actualList = new SuperAwesomeNameService().doTheThings(testNames);

        List<String> expectedList = asList("christina", "Eevee", "Kate", "Zil", "Olivia", "Haras");
        assertEquals(expectedList, actualList);
        System.out.println("This will have to do. It is not perfect but we're out of budget! Go ahead and submit this to production!");
    }

    protected static boolean isTest6Success() {
        Test6 test6 = new Test6();
        try {
            test6.actualTest6();
        } catch (java.lang.AssertionError error) {
            return false;
        }
        return true;
    }
}
