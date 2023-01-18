import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Test2 {
    @Test
    public void test2() {
        if(Test5.isTest5Success() || Test6.isTest6Success()){
            return;
        }
        if(!Test1.isTest1Success()){
            fail("First make sure Test1 has passed");
        }

        List<String> testNames = asList("Sarah", "christina", "Emma");

        List<String> actualList = new SuperAwesomeNameService().doTheThings(testNames);

        List<String> expectedList = asList("christina", "Emma", "Sarah");
        assertEquals(expectedList, actualList);
        System.out.println("Actually I don't like names with an 'e' in them, can you filter them out for me?");
    }

    protected static boolean isTest2Success(){
        Test2 test2 = new Test2();
        try {
            test2.test2();
        } catch (java.lang.AssertionError error){
            return false;
        }
        return true;
    }
}
