import java.time.DayOfWeek;
import java.time.Month;
import java.time.Year;

import static java.time.DayOfWeek.MONDAY;

/**
 * This class represents a month as shown on a calendar
 */
public class CalendarMonth {
    private Year year;
    private Month month;

    public CalendarMonth(Year year, Month month) {
        this.year = year;
        this.month = month;
    }

    public String[][] toArrays() {
        // TODO: start implementing here
        return toArrays(MONDAY);
    }

    public String[][] toArrays(DayOfWeek firstDayOfWeek) {
        // TODO: implement here for extra points
        return new String[][] {};
    }
}
