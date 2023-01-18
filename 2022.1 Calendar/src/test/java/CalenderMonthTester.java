import java.time.DayOfWeek;
import java.time.Month;
import java.time.Year;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertArrayEquals;

/**
 * Helper class for all the tests
 */
public class CalenderMonthTester {

    void test(Year year, Month month, String[][] expected) {
        System.out.println("Testing " + month + " " + year + ", starting week at " + DayOfWeek.of(1));
        CalendarPrinter printer = new CalendarPrinter();

        CalendarMonth calendarMonth = new CalendarMonth(year, month);
        var monthArray = calendarMonth.toArrays();
        printer.printToConsole(monthArray);

        System.out.println("Array to test: " + toString(monthArray));
        assertArrayEquals(expected, monthArray);
    }

    void test(Year year, Month month, DayOfWeek firstDayOfWeek, String[][] expected) {
        System.out.println("Testing " + month + " " + year + ", starting week at " + firstDayOfWeek);
        CalendarPrinter printer = new CalendarPrinter();

        CalendarMonth calendarMonth = new CalendarMonth(year, month);
        var monthArray = calendarMonth.toArrays(firstDayOfWeek);
        printer.printToConsole(monthArray);

        System.out.println("Array to test: " + toString(monthArray));
        assertArrayEquals(expected, monthArray);
    }

    private String toString(String[][] month) {
        return asList(month)
                .stream()
                .map(week -> asList(week)
                        .stream()
                        .collect(joining("\", \"", "{\"", "\"}")))
                .collect(joining(", ", "{", "}"));
    }
}
