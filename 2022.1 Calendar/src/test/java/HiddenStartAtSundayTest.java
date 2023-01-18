import org.junit.Test;

import java.time.Month;
import java.time.Year;

import static java.time.DayOfWeek.SUNDAY;

/**
 * This class tests other months to make sure the months aren't hardcoded
 */
public class HiddenStartAtSundayTest extends CalenderMonthTester {

    @Test
    public void testFebruary2020() {
        String[][] expectedArray = {{" ", " ", " ", " ", " ", " ", "1"}, {"2", "3", "4", "5", "6", "7", "8"}, {"9", "10", "11", "12", "13", "14", "15"}, {"16", "17", "18", "19", "20", "21", "22"}, {"23", "24", "25", "26", "27", "28", "29"}};
        test(Year.of(2020), Month.FEBRUARY, SUNDAY, expectedArray);
    }

    @Test
    public void testFebruary1959() {
        String[][] expectedArray = {{"1", "2", "3", "4", "5", "6", "7"}, {"8", "9", "10", "11", "12", "13", "14"}, {"15", "16", "17", "18", "19", "20", "21"}, {"22", "23", "24", "25", "26", "27", "28"}};
        test(Year.of(1959), Month.FEBRUARY, SUNDAY, expectedArray);
    }
}
