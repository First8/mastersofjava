import java.time.LocalDate;
import java.time.Period;

class Person {
    private final LocalDate birthDate;

    public Person(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int calcAgeAt(LocalDate date) {
        return Period.between(this.birthDate, date).getYears();
    }
}
