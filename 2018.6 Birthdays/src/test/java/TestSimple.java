
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import org.junit.Test;

public class TestSimple {

    @Test
    public void testSimple() {
        Family family = new Family(Arrays.asList( //
                new Person(LocalDate.of(1984, Month.OCTOBER, 15)), //
                new Person(LocalDate.of(1983, Month.DECEMBER, 7)), //
                new Person(LocalDate.of(1981, Month.MAY, 15)), //
                new Person(LocalDate.of(1953, Month.JUNE, 10)), //
                new Person(LocalDate.of(1951, Month.JULY, 24))));
        assertEquals(LocalDate.of(2021, Month.JUNE, 10), family.getDateWhenFamilyIs(250));
    }

    // tries to catch the error of starting with LocalDate.now() and counting up
    @Test
    public void testPast() {
        Family family = new Family(Arrays.asList( //
                new Person(LocalDate.of(1984, Month.OCTOBER, 15)), //
                new Person(LocalDate.of(1983, Month.DECEMBER, 7)), //
                new Person(LocalDate.of(1981, Month.MAY, 15)), //
                new Person(LocalDate.of(1953, Month.JUNE, 10)), //
                new Person(LocalDate.of(1951, Month.JULY, 24))));
        assertEquals(LocalDate.of(2011, Month.JUNE, 10), family.getDateWhenFamilyIs(200));
    }

}
