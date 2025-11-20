import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class Test5 {
    private Planner planner;

    @BeforeEach
    public void planner() {
        planner = new Planner();
    }

    @Test
    public void previousTestCheck() {
        if(!Test4.isTest4Success()) {
            fail("First make sure Test4 has passed");
        }
    }

    @Test
    public void testAddAppointmentMorningMonday() {
        Appointment appointment = new Appointment("Appointment Monday",
                LocalDateTime.of(2025, 3, 31, 9, 0),
                LocalDateTime.of(2025, 3, 31, 10, 0));
        Exception exception = assertThrows(Exception.class, () -> {
            planner.addAppointment(appointment);
        });
        assertEquals("No appointments can be scheduled on Monday and Tuesday mornings", exception.getMessage());
    }

    @Test
    public void testAddAppointmentMorningTuesday() {
        Appointment appointment = new Appointment("Appointment Tuesday",
                LocalDateTime.of(2025, 4, 1, 10, 0),
                LocalDateTime.of(2025, 4, 1, 11, 0));
        Exception exception = assertThrows(Exception.class, () -> {
            planner.addAppointment(appointment);
        });

        String expectedMessage = "No appointments can be scheduled on Monday and Tuesday mornings";
        assertEquals(expectedMessage, exception.getMessage(), "Exception expected with the message: \"" + expectedMessage + "\"");

    }

    @Test
    public void testAddAppointmentAfternoonFriday() {
        Appointment appointment = new Appointment("Report Review",
                LocalDateTime.of(2025, 4, 4, 14, 0),
                LocalDateTime.of(2025, 4, 4, 15, 0));
        Exception exception = assertThrows(Exception.class, () -> {
            planner.addAppointment(appointment);
        });
        assertEquals("No appointments can be scheduled on Friday afternoons", exception.getMessage());
        System.out.println("\nOh, I completely forgot to mention a special company day called 'Whimsical Wonder Wednesday':\n" +
                "On this special day we will be doing Whimsical activities, so no appointments can be scheduled for that day \n" +
                "Whimsical Wonder Wednesday is every year on the third Wednesday of May.");
    }

    protected static boolean isTest5Success(){
        Test5 exceptionDayAppointmentsTest = new Test5();
        try {
            exceptionDayAppointmentsTest.planner();
            exceptionDayAppointmentsTest.testAddAppointmentMorningMonday();
            exceptionDayAppointmentsTest.testAddAppointmentMorningTuesday();
            exceptionDayAppointmentsTest.testAddAppointmentAfternoonFriday();
        } catch (Exception | Error e) {
            return false;
        }
        return true;
    }

}