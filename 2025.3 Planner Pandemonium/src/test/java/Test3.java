import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class Test3 {
    private Planner planner;

    @BeforeEach
    public void planner() {
        planner = new Planner();
    }

    @Test
    public void testAddAppointmentWithInvalidTime() {
        if(!Test2.isTest2Success()) {
            fail("First make sure Test2 has passed");
        }
        Appointment appointment = new Appointment("Invalid Appointment",
                LocalDateTime.of(2025, 4, 4, 11, 0),
                LocalDateTime.of(2025, 4, 4, 10, 0));

        Exception exception = assertThrows(Exception.class, () -> {
            planner.addAppointment(appointment);
        });

        String expectedMessage = "End time must be after start time";
        assertEquals(expectedMessage, exception.getMessage(), "Exception expected with the message: \"" + expectedMessage + "\"");

        System.out.println("\nI have quite a long drive ahead, and to avoid rush hour, I leave early.\n" +
                "Please ensure that appointments can only be scheduled from the start of my working day (08:00) until 15:00!");
    }

    protected static boolean isTest3Success(){
        Test3 validBeginEndTimeTest = new Test3();
        try {
            validBeginEndTimeTest.planner();
            validBeginEndTimeTest.testAddAppointmentWithInvalidTime();
        } catch (Exception | Error e) {
            return false;
        }
        return true;
    }
}
