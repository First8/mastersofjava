import java.time.LocalDateTime;

public class Appointment {
    private String appointmentName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Appointment(String appointmentName, LocalDateTime startTime, LocalDateTime endTime) {
        this.appointmentName = appointmentName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getAppointmentName() {
        return appointmentName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return String.format("Appointment name: %s | StartTime: %s | EndTime: %s", appointmentName, startTime, endTime);
    }
}