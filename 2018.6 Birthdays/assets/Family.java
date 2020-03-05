import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Family {
    private List<Person> persons;

    public Family(List<Person> persons) {
        this.persons = new ArrayList<>(persons);
    }

    public long getTotalAgeAt(LocalDate date) {
        return persons.stream().mapToLong(p -> p.calcAgeAt(date)).sum();
    }

    public LocalDate getDateWhenFamilyIs(long totalYearsOld) {
        LocalDate guessLow = LocalDate.MIN;
        LocalDate guessMiddle = guessLow.plusDays(1);
        LocalDate guessHigh = LocalDate.MAX;
        while (!(getTotalAgeAt(guessLow) < totalYearsOld && getTotalAgeAt(guessHigh) >= totalYearsOld && guessLow.plusDays(1).equals(guessHigh))) {
            long days = Duration.between(guessLow.atTime(0, 0), guessHigh.atTime(0, 0)).toDays();
            guessMiddle = guessLow.plusDays((long) Math.floor(days / 2));
            if (getTotalAgeAt(guessMiddle) < totalYearsOld) {
                // guess is too high
                guessLow = guessMiddle;
            } else {
                guessHigh = guessMiddle;
            }
            System.out.println(days + " " + guessLow + " " + guessMiddle + " " + guessHigh + " " + getTotalAgeAt(guessMiddle));
        }
        return guessHigh;
    }
}
