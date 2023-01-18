import org.junit.Test;

import java.time.Month;
import java.time.Year;

/**
 * This class tests other months to make sure the months aren't hardcoded
 */
public class HiddenSimpleTests extends CalenderMonthTester {
    @Test
    public void testJuly2022() {
        String[][] expectedArray = {{" ", " ", " ", " ", "1", "2", "3"}, {"4", "5", "6", "7", "8", "9", "10"}, {"11", "12", "13", "14", "15", "16", "17"}, {"18", "19", "20", "21", "22", "23", "24"}, {"25", "26", "27", "28", "29", "30", "31"}};
        test(Year.of(2022), Month.JULY, expectedArray);
    }

    @Test
    public void testAugust1984() {
        String[][] expectedArray = {{" ", " ", "1", "2", "3", "4", "5"}, {"6", "7", "8", "9", "10", "11", "12"}, {"13", "14", "15", "16", "17", "18", "19"}, {"20", "21", "22", "23", "24", "25", "26"}, {"27", "28", "29", "30", "31", " ", " "}};
        test(Year.of(1984), Month.AUGUST, expectedArray);
    }
}
