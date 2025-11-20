import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Test2 {
    private Planner planner;

    @BeforeEach
    public void planner() {
        planner = new Planner();
    }

    @Test
    public void testOverlappingAppointments() {
        if(!Test1.isTest1Success()) {
            fail("First make sure Test1 has passed");
        }
        Appointment appointment1 = new Appointment("Appointment 1",
                LocalDateTime.of(2025, 4, 2, 9, 0),
                LocalDateTime.of(2025, 4, 2, 10, 0));

        Appointment appointment2 = new Appointment("Appointment 2",
                LocalDateTime.of(2025, 4, 2, 9, 30),
                LocalDateTime.of(2025, 4, 2, 10, 30));

        try {
            planner.addAppointment(appointment1);
            planner.addAppointment(appointment2);
            fail("Expected an Exception for overlapping appointments.");
        } catch (Exception e) {
            assertEquals("Appointment overlaps with another appointment", e.getMessage());
        }

        System.out.println("\nI want to ensure that all appointments are valid, so please verify that the end time is after the start time \n" +
                "If not, an exception should be thrown with the message: 'End time must be after start time'");
    }

    protected static boolean isTest2Success(){
        Test2 noOverlappingAppointmentTest = new Test2();
        try {
            noOverlappingAppointmentTest.planner();
            noOverlappingAppointmentTest.testOverlappingAppointments();
        } catch (Exception | Error e) {
            return false;
        }
        return true;
    }
}