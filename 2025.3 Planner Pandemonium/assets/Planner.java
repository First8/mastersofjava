import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Planner {
    public Calendar calendar = new Calendar();

    public void addAppointment(Appointment appointment) throws Exception {
        if (appointment.getEndTime().isBefore(appointment.getStartTime())) {
            throw new Exception("End time must be after start time");
        }

        LocalDateTime startDateTime = getStartDateTime(appointment);
        if (isSpecialDay(startDateTime.toLocalDate())) {
            throw new Exception("No appointments are allowed on Whimsical Wonder Wednesday");
        }

        for (Appointment existingAppointment : calendar.getAppointments()) {
            if (existingAppointment.getStartTime().isBefore(appointment.getEndTime()) &&
                    existingAppointment.getEndTime().isAfter(appointment.getStartTime())) {
                throw new Exception("Appointment overlaps with another appointment");
            }
        }

        calendar.appointments.add(appointment);
    }

    private static LocalDateTime getStartDateTime(Appointment appointment) throws Exception {
        LocalTime startTime = appointment.getStartTime().toLocalTime();
        LocalTime endTime = appointment.getEndTime().toLocalTime();
        LocalDateTime startDateTime = appointment.getStartTime();

        if (startTime.isBefore(LocalTime.of(8, 0)) || endTime.isAfter(LocalTime.of(15, 0))) {
            throw new Exception("Appointments can only be scheduled between 08:00 and 15:00");
        }

        if ((startDateTime.getDayOfWeek() == DayOfWeek.MONDAY ||
                startDateTime.getDayOfWeek() == DayOfWeek.TUESDAY) &&
                startTime.isBefore(LocalTime.of(12,0))) {
            throw new Exception("No appointments can be scheduled on Monday and Tuesday mornings");
        }

        if (startDateTime.getDayOfWeek() == DayOfWeek.FRIDAY && startTime.isAfter(LocalTime.of(12,0))) {
            throw new Exception("No appointments can be scheduled on Friday afternoons");
        }
        return startDateTime;
    }

    private boolean isSpecialDay(LocalDate date) {
        return date.getMonthValue() == 5 && date.getDayOfWeek() == DayOfWeek.WEDNESDAY &&
                date.getDayOfMonth() >= 15 && date.getDayOfMonth() <= 21;
    }
}