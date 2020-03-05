
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import org.junit.Test;

public class TestMultipleOnOneDay {
    // tries to catch the error where your program will keep looping when your family goes from being a total of 2 years
    // old to a total of 4 years old at the same date
    @Test
    public void testMultipleBirthDaysOnOneDay() {
        Family family = new Family(Arrays.asList(new Person(LocalDate.of(1984, Month.DECEMBER, 31)), //
                new Person(LocalDate.of(1984, Month.DECEMBER, 31))));
        assertEquals(LocalDate.of(1986, Month.DECEMBER, 31), family.getDateWhenFamilyIs(3));
    }

}
