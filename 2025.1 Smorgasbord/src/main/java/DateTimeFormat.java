import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormat {
    public static String dateFormatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");

        return formatter.format(Instant.now());
    }
}