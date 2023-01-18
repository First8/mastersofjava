import org.junit.Test;

import java.time.Month;
import java.time.Year;

/**
 * This class tests another month to make sure the months aren't hardcoded
 */
public class HiddenShortMonthTest extends CalenderMonthTester {
    @Test
    public void testFebruary1965() {
        String[][] expectedArray = {{"1", "2", "3", "4", "5", "6", "7"}, {"8", "9", "10", "11", "12", "13", "14"}, {"15", "16", "17", "18", "19", "20", "21"}, {"22", "23", "24", "25", "26", "27", "28"}};
        test(Year.of(1965), Month.FEBRUARY, expectedArray);
    }

    // To prevent people from just putting an `if (month.equals(FEBRUARY)) return {{"1", ...` statement in the code also run a februari that doens't fall in 4 weeks
    @Test
    public void testFebruary2019() {
        String[][] expectedArray =
                {{" ", " ", " ", " ", "1", "2", "3"},
                        {"4", "5", "6", "7", "8", "9", "10"},
                        {"11", "12", "13", "14", "15", "16", "17"},
                        {"18", "19", "20", "21", "22", "23", "24"},
                        {"25", "26", "27", "28", " ", " ", " "}};
        test(Year.of(2019), Month.FEBRUARY, expectedArray);
    }
}
