import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestDateTimeFormat {
        @Test
        public void testFormatCurrentDateTime() {
            String formattedDateTime = DateTimeFormat.dateFormatter();

            assertNotNull(formattedDateTime);

            assertTrue(formattedDateTime.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}"));
        }
}