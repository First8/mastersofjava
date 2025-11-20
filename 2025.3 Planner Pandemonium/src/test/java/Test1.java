import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class Test1 {
    private Planner planner;

    @BeforeEach
    public void planner() {
        planner = new Planner();
    }

    @Test
    public void testAddAppointment() throws Exception {
        Appointment appointment = new Appointment("Appointment",
                LocalDateTime.of(2025, 4, 4, 10, 0),
                LocalDateTime.of(2025, 4, 4, 11, 0));

        planner.addAppointment(appointment);
        assertEquals(1, planner.calendar.getAppointments().size());
        assertEquals(appointment, planner.calendar.getAppointments().getFirst());

        System.out.println("Please ensure that no overlapping appointments can be scheduled. \n" +
                "If an overlap occurs, an exception should be thrown with the message: 'Appointment overlaps with another appointment'");
    }

    protected static boolean isTest1Success(){
        Test1 addAppointmentTest = new Test1();
        try {
            addAppointmentTest.planner();
            addAppointmentTest.testAddAppointment();
        } catch (Exception | Error e) {
            return false;
        }
        return true;
    }
}