import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.ZonedDateTime;

public class DateTimeFormat {
    public static String dateFormatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
        ZonedDateTime now = ZonedDateTime.now();

        return formatter.format(now);

    }
}
