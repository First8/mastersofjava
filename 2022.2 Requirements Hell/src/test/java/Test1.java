import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class Test1 {
    @Test
    public void test1() {
        if(Test5.isTest5Success() || Test6.isTest6Success()){
            return;
        }

        List<String> testNames = asList("Sarah", "Christina", "Emma");

        List<String> actualList = new SuperAwesomeNameService().doTheThings(testNames);

        List<String> expectedList = asList("Christina", "Emma", "Sarah");
        assertEquals(expectedList, actualList);
        System.out.println("I forgot to tell, when some name starts with a lower case, it still has to sort alphabetical.");
    }

    protected static boolean isTest1Success(){
        Test1 test1 = new Test1();
        try {
            test1.test1();
        } catch (java.lang.AssertionError error){
            return false;
        }
        return true;
    }
}
