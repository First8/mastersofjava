import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class Test4 {
    private Planner planner;

    @BeforeEach
    public void planner() {
        planner = new Planner();
    }

    @Test
    public void previousTestCheck() {
        if(!Test3.isTest3Success()) {
            fail("First make sure Test3 has passed");
        }
    }

    @Test
    public void testAddAppointmentAtInvalidTimeOfDay() {
        LocalDateTime startTime = LocalDateTime.of(2025, 3, 31, 16, 0);
        LocalDateTime endTime = LocalDateTime.of(2025, 3, 31, 17, 0);
        Appointment appointment = new Appointment("Invalid Time", startTime, endTime);

        Exception exception = assertThrows(Exception.class, () -> {
            planner.addAppointment(appointment);
        });

        String expectedMessage = "Appointments can only be scheduled between 08:00 and 15:00";
        assertEquals(expectedMessage, exception.getMessage(), "Exception expected with the message: \"" + expectedMessage + "\"");
    }

    @Test
    public void testAddAppointmentAtValidTimeOfDay() throws Exception {
        LocalDateTime startTime = LocalDateTime.of(2025, 4, 2, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2025, 4, 2, 11, 0);
        Appointment appointment = new Appointment("Valid Time", startTime, endTime);

        planner.addAppointment(appointment);

        assertEquals(1, planner.calendar.getAppointments().size());
        System.out.println("\nTo create a less pressuring schedule, we will not schedule any appointments in the morning on the following days:\n" +
                "- Monday\n" +
                "- Tuesday\n" +
                "Additionally, on Fridays, no appointments will be scheduled in the afternoon (no meetings starting later than 12:00.");
    }

    protected static boolean isTest4Success(){
        Test4 timeOfAppointmentTest = new Test4();
        try {
            timeOfAppointmentTest.planner();
            timeOfAppointmentTest.testAddAppointmentAtValidTimeOfDay();
            timeOfAppointmentTest.testAddAppointmentAtInvalidTimeOfDay();
        } catch (Exception | Error e ) {
            return false;
        }
        return true;
    }
}