import java.util.ArrayList;
import java.util.List;

public class Calendar {
    public List<Appointment> appointments = new ArrayList<>();

    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }
}