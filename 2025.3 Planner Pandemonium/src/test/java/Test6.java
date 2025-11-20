import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class Test6 {
    private Planner planner;

    @BeforeEach
    public void planner() {
        planner = new Planner();
    }

    @Test
    public void testAddAppointmentOnSpecialDay() {
        if(!Test5.isTest5Success()) {
            fail("First make sure Test5 has passed");
        }
        LocalDateTime startTime = LocalDateTime.of(2025, 5, 21, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2025, 5, 21, 10, 0);
        Appointment appointment = new Appointment("Special day appointment", startTime, endTime);

        Exception exception = assertThrows(Exception.class, () -> {
            planner.addAppointment(appointment);
        });

        String expectedMessage = "No appointments are allowed on Whimsical Wonder Wednesday";
        assertEquals(expectedMessage, exception.getMessage(), "Exception expected with the message: \"" + expectedMessage + "\"");

        System.out.println("\nThat should do it for now. Let's get this to production! \n" +
                "Remember, if you ever need better instructions, you can always consult the crystal ball!");
    }
}