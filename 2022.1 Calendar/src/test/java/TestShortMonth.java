import org.junit.Test;

import java.time.Month;
import java.time.Year;

/**
 * This class tests there is no empty row at the end of a short month
 */
public class TestShortMonth extends CalenderMonthTester {

    // These tests assume the week starts at monday

    @Test
    public void testFebruary2021() {
        String[][] expectedArray = {{"1", "2", "3", "4", "5", "6", "7"}, {"8", "9", "10", "11", "12", "13", "14"}, {"15", "16", "17", "18", "19", "20", "21"}, {"22", "23", "24", "25", "26", "27", "28"}};
        test(Year.of(2021), Month.FEBRUARY, expectedArray);
    }
}
