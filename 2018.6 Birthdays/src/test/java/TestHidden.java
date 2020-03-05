
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestHidden {

    @Test
    public void testSimple() {
        Family family = new Family(Arrays.asList( //
                new Person(LocalDate.of(1974, Month.OCTOBER, 15)), //
                new Person(LocalDate.of(1973, Month.DECEMBER, 7)), //
                new Person(LocalDate.of(1971, Month.MAY, 15)), //
                new Person(LocalDate.of(1973, Month.JUNE, 10)), //
                new Person(LocalDate.of(1971, Month.JULY, 24))));
        assertEquals(LocalDate.of(2023, Month.JUNE, 10), family.getDateWhenFamilyIs(250));
    }

    // tries to catch the error of starting with LocalDate.now() and counting up
    @Test
    public void testPast() {
        Family family = new Family(Arrays.asList( //
                new Person(LocalDate.of(1994, Month.OCTOBER, 15)), //
                new Person(LocalDate.of(1993, Month.DECEMBER, 7)), //
                new Person(LocalDate.of(1991, Month.MAY, 15)), //
                new Person(LocalDate.of(1993, Month.JUNE, 10)), //
                new Person(LocalDate.of(1991, Month.JULY, 24))));
        assertEquals(LocalDate.of(2033, Month.JUNE, 10), family.getDateWhenFamilyIs(200));
    }

    // tries to catch a possible error where you are off-by-one for the first of January
    @Test
    public void test1Jan() {
        Family family = new Family(Arrays.asList( //
                new Person(LocalDate.of(1954, Month.JANUARY, 1)), //
                new Person(LocalDate.of(1953, Month.DECEMBER, 7)), //
                new Person(LocalDate.of(1951, Month.MAY, 15)), //
                new Person(LocalDate.of(1953, Month.JUNE, 10)), //
                new Person(LocalDate.of(1951, Month.JULY, 24))));
        assertEquals(LocalDate.of(1993, Month.JANUARY, 1), family.getDateWhenFamilyIs(199));
    }

    // tries to catch a possible error where you are off-by-one for the last day of the year
    @Test
    public void test31Dec() {
        Family family = new Family(Arrays.asList( //
                new Person(LocalDate.of(1974, Month.DECEMBER, 31)), //
                new Person(LocalDate.of(1973, Month.DECEMBER, 7)), //
                new Person(LocalDate.of(1971, Month.MAY, 15)), //
                new Person(LocalDate.of(1943, Month.JUNE, 10)), //
                new Person(LocalDate.of(1941, Month.JULY, 24))));
        assertEquals(LocalDate.of(2001, Month.DECEMBER, 31), family.getDateWhenFamilyIs(203));
    }

    // tries to catch the error where your program will keep looping when your family goes from being a total of 2 years
    // old to a total of 4 years old at the same date
    @Test
    public void testMultipleBirthDaysOnOneDay() {
        Family family = new Family(Arrays.asList(new Person(LocalDate.of(1984, Month.DECEMBER, 31)), //
                new Person(LocalDate.of(1994, Month.DECEMBER, 31))));
        assertEquals(LocalDate.of(1991, Month.JANUARY, 1), family.getDateWhenFamilyIs(3));
    }

    // does your code work for big families? or will it take too long...
    @Test
    public void testBigFamily() {
        List<Person> persons = new ArrayList<>();
        LocalDate someDay = LocalDate.of(2016, Month.FEBRUARY, 1);
        for (int i = 0; i < 100000; i++) {
            persons.add(new Person(someDay.minusDays(i)));
        }
        Family family = new Family(persons);
        assertEquals(LocalDate.of(2029, Month.SEPTEMBER, 10), family.getDateWhenFamilyIs(15000000));
    }

    // does your code with high values? or will it take too long...
    @Test
    public void testHighNumbers() {
        Family family = new Family(Arrays.asList(//
                new Person(LocalDate.of(1954, Month.DECEMBER, 31)), //
                new Person(LocalDate.of(1953, Month.DECEMBER, 7)), //
                new Person(LocalDate.of(1931, Month.FEBRUARY, 15)), //
                new Person(LocalDate.of(1963, Month.JUNE, 10)), //
                new Person(LocalDate.of(1921, Month.JULY, 24))));
        assertEquals(LocalDate.of(980001945, Month.DECEMBER, 31), family.getDateWhenFamilyIs(4900000003L));
    }

    // does your code with high values AND big families? or will it take too long...
    @Test
    public void testBigFamilyWithHighNumbers() {
        List<Person> persons = new ArrayList<>();
        LocalDate someDay = LocalDate.of(2016, Month.FEBRUARY, 1);
        for (int i = 0; i < 100000; i++) {
            persons.add(new Person(someDay.minusDays(i)));
        }
        Family family = new Family(persons);
        assertEquals(LocalDate.of(150001879, Month.SEPTEMBER, 10), family.getDateWhenFamilyIs(15000000000000L));
    }

}
