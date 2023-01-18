import java.time.DayOfWeek;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        YearMonth yearMonth = YearMonth.of(year.getValue(), month);
        DayOfWeek firstDay = yearMonth.atDay(1).getDayOfWeek();
        var lengthOfMonth = yearMonth.lengthOfMonth();

        var numberEmptyAtStartOfMonth = firstDay.getValue() - 1;
        var numberEmptyAtEndOfMonth = (6 * 7 - lengthOfMonth - numberEmptyAtStartOfMonth) % 7;
        var emptyAtStartOfMonth = Stream.generate(() -> " ").limit(numberEmptyAtStartOfMonth);
        var emptyAtEndOfMonth = Stream.generate(() -> " ").limit(numberEmptyAtEndOfMonth);
        var values = IntStream.rangeClosed(1, lengthOfMonth)
                .mapToObj(String::valueOf);

        var stringList = Stream.concat(Stream.concat(emptyAtStartOfMonth, values), emptyAtEndOfMonth)
                .toList();

        int numberOfRows;
        if (stringList.size() <= 4 * 7) {
            numberOfRows = 4;
        } else if (stringList.size() <= (5 * 7)) {
            numberOfRows = 5;
        } else {
            numberOfRows = 6;
        }

        return IntStream.rangeClosed(0, numberOfRows - 1)
                .map(i -> i * 7) // startindex in the list of each row
                .mapToObj(i -> stringList.subList(i, i + 7)
                        .toArray(String[]::new))
                .toArray(String[][]::new);
    }

    public String[][] toArrays(DayOfWeek firstDayOfWeek) {
        YearMonth yearMonth = YearMonth.of(year.getValue(), month);
        DayOfWeek firstDay = yearMonth.atDay(1).getDayOfWeek();
        var lengthOfMonth = yearMonth.lengthOfMonth();

        var numberEmptyAtStartOfMonth = (firstDay.getValue() - firstDayOfWeek.getValue() + 7) % 7;
        var numberEmptyAtEndOfMonth = (6 * 7 - lengthOfMonth - numberEmptyAtStartOfMonth) % 7;
        var emptyAtStartOfMonth = Stream.generate(() -> " ").limit(numberEmptyAtStartOfMonth);
        var emptyAtEndOfMonth = Stream.generate(() -> " ").limit(numberEmptyAtEndOfMonth);
        var values = IntStream.rangeClosed(1, lengthOfMonth)
                .mapToObj(String::valueOf);

        var stringList = Stream.concat(Stream.concat(emptyAtStartOfMonth, values), emptyAtEndOfMonth)
                .toList();

        int numberOfRows;
        if (stringList.size() <= 4 * 7) {
            numberOfRows = 4;
        } else if (stringList.size() <= (5 * 7)) {
            numberOfRows = 5;
        } else {
            numberOfRows = 6;
        }
        return IntStream.rangeClosed(0, numberOfRows - 1)
                .map(i -> i * 7) // startindex in the list of each row
                .mapToObj(i -> stringList.subList(i, i + 7)
                        .toArray(String[]::new))
                .toArray(String[][]::new);
    }
}
