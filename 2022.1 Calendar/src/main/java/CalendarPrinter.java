import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;

/**
 * This class prints a m x n array as a month on a calendar without headers.
 */
public class CalendarPrinter {

    public void printToConsole(String[][] month) {
        asList(month)
                .stream()
                .map(week -> asList(week)
                        .stream()
                        .map(this::expand)
                        .collect(joining(" | ", "| ", " |")))
                .forEach(System.out::println);
    }

    private String expand(String day) {
        return day.length() == 1 ? " " + day : day;
    }
}
