import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/**
 * Test other names
 */
public class HiddenTest  {
    @Test
    public void testDoTheThings() {
        List<String> testNames = asList("Luna", "Sophia", "Emily", "amelia");

        List<String> actualList = new SuperAwesomeNameService().doTheThings(testNames);

        List<String> expectedList = asList("amelia", "Ylime", "Luna", "Aihpos");
        assertEquals(expectedList, actualList);
        System.out.println("Awesome!");
    }
}
