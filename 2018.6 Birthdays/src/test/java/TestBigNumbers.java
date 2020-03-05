
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestBigNumbers {

    // does your code work for big families? or will it take too long...
    @Test
    public void testBigFamily() {
        List<Person> persons = new ArrayList<>();
        LocalDate someDay = LocalDate.of(2018, Month.JUNE, 1);
        for (int i = 0; i < 100000; i++) {
            persons.add(new Person(someDay.minusDays(i)));
        }
        Family family = new Family(persons);
        assertEquals(LocalDate.of(2032, Month.JANUARY, 8), family.getDateWhenFamilyIs(15000000));
    }

    // does your code with high values? or will it take too long...
    @Test
    public void testHighNumbers() {
        Family family = new Family(Arrays.asList(//
                new Person(LocalDate.of(1984, Month.DECEMBER, 31)), //
                new Person(LocalDate.of(1983, Month.DECEMBER, 7)), //
                new Person(LocalDate.of(1981, Month.MAY, 15)), //
                new Person(LocalDate.of(1953, Month.JUNE, 10)), //
                new Person(LocalDate.of(1951, Month.JULY, 24))));
        assertEquals(LocalDate.of(980001971, Month.DECEMBER, 31), family.getDateWhenFamilyIs(4900000003L));
    }

    // does your code with high values AND big families? or will it take too long...
    @Test
    public void testBigFamilyWithHighNumbers() {
        List<Person> persons = new ArrayList<>();
        LocalDate someDay = LocalDate.of(2018, Month.JUNE, 1);
        for (int i = 0; i < 100000; i++) {
            persons.add(new Person(someDay.minusDays(i)));
        }
        Family family = new Family(persons);
        assertEquals(LocalDate.of(150001882, Month.JANUARY, 8), family.getDateWhenFamilyIs(15000000000000L));
    }

}
