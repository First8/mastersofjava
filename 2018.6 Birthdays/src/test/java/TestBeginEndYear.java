
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import org.junit.Test;

public class TestBeginEndYear {

    // tries to catch a possible error where you are off-by-one for the first of January
    @Test
    public void test1Jan() {
        Family family = new Family(Arrays.asList( //
                new Person(LocalDate.of(1984, Month.JANUARY, 1)), //
                new Person(LocalDate.of(1983, Month.DECEMBER, 7)), //
                new Person(LocalDate.of(1981, Month.MAY, 15)), //
                new Person(LocalDate.of(1953, Month.JUNE, 10)), //
                new Person(LocalDate.of(1951, Month.JULY, 24))));
        assertEquals(LocalDate.of(2011, Month.JANUARY, 1), family.getDateWhenFamilyIs(199));
    }

    // tries to catch a possible error where you are off-by-one for the last day of the year
    @Test
    public void test31Dec() {
        Family family = new Family(Arrays.asList( //
                new Person(LocalDate.of(1984, Month.DECEMBER, 31)), //
                new Person(LocalDate.of(1983, Month.DECEMBER, 7)), //
                new Person(LocalDate.of(1981, Month.MAY, 15)), //
                new Person(LocalDate.of(1953, Month.JUNE, 10)), //
                new Person(LocalDate.of(1951, Month.JULY, 24))));
        assertEquals(LocalDate.of(2011, Month.DECEMBER, 31), family.getDateWhenFamilyIs(203));
    }

}
