import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Test3 {
    @Test
    public void test3() {
        if (Test5.isTest5Success() || Test6.isTest6Success()) {
            return;
        }
        if (!Test2.isTest2Success()) {
            fail("First make sure Test2 has passed");
        }

        List<String> testNames = asList("Sarah", "christina", "Emma", "Kate");

        List<String> actualList = new SuperAwesomeNameService().doTheThings(testNames);

        List<String> expectedList = asList("christina", "Emma", "Sarah");
        assertEquals(expectedList, actualList);
        System.out.println("I like the names with two consecutive vowels (like on of 'aeiou') in them more, they should \n" +
                "be sorted first, before all other names.");
    }

    protected static boolean isTest3Success() {
        Test3 test3 = new Test3();
        try {
            test3.test3();
        } catch (java.lang.AssertionError error) {
            return false;
        }
        return true;
    }
}
