import org.junit.Test;

import java.time.Month;
import java.time.Year;

import static java.time.DayOfWeek.SUNDAY;

/**
 * Start the week at sunday
 */
public class TestStartAtSunday extends CalenderMonthTester {

    @Test
    public void testFebruary2021() {
        String[][] expectedArray = {{" ", "1", "2", "3", "4", "5", "6"}, {"7", "8", "9", "10", "11", "12", "13"}, {"14", "15", "16", "17", "18", "19", "20"}, {"21", "22", "23", "24", "25", "26", "27"}, {"28", " ", " ", " ", " ", " ", " "}};
        test(Year.of(2021), Month.FEBRUARY, SUNDAY, expectedArray);
    }

    @Test
    public void testNovember2022() {
        String[][] expectedArray = {{" ", " ", "1", "2", "3", "4", "5"}, {"6", "7", "8", "9", "10", "11", "12"}, {"13", "14", "15", "16", "17", "18", "19"}, {"20", "21", "22", "23", "24", "25", "26"}, {"27", "28", "29", "30", " ", " ", " "}};
        test(Year.of(2022), Month.NOVEMBER, SUNDAY, expectedArray);
    }

    @Test
    public void testFebruary1953() {
        String[][] expectedArray = {{"1", "2", "3", "4", "5", "6", "7"}, {"8", "9", "10", "11", "12", "13", "14"}, {"15", "16", "17", "18", "19", "20", "21"}, {"22", "23", "24", "25", "26", "27", "28"}};
        test(Year.of(1953), Month.FEBRUARY, SUNDAY, expectedArray);
    }
}
