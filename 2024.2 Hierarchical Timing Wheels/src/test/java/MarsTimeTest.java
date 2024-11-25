import org.junit.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import static java.util.Collections.emptyList;
import static java.util.Collections.nCopies;
import static org.junit.Assert.assertEquals;

public class MarsTimeTest {

    static class MarsTimer extends Timer {
        private final int year;
        private final int day;
        private final int minute;

        MarsTimer(int year, int day, int minute, Runnable expiryMethod) {
            super(expiryMethod);
            this.year = year;
            this.day = day;
            this.minute = minute;
        }
    }

    /**
     * Creates a hierarchical timing wheel with minutes, days and years in mars time.
     *
     * @return a wheel
     */
    private HierarchicalTimingWheel createMarsWheel() {
        HierarchicalTimingWheel smallestWheel =
                new HierarchicalTimingWheel(1480, t -> ((MarsTimer) t).minute);
        smallestWheel.addBiggerWheel(670, t -> ((MarsTimer) t).day)
                .addBiggerWheel(10, t -> ((MarsTimer) t).year);
        return smallestWheel;
    }


    @Test
    public void testTwoMinutes() {
        HierarchicalTimingWheel wheel = createMarsWheel();

        Timer twoMinuteTimer = new MarsTimer(0, 0, 2, () -> System.out.println("timer TwoMinutes expired"));
        wheel.add(twoMinuteTimer);

        List<Timer> expiredTimers = wheel.advance();
        assertEquals(emptyList(), expiredTimers);

        expiredTimers = wheel.advance();
        assertEquals(List.of(twoMinuteTimer), expiredTimers);
    }


    @Test
    public void testOneYearTwoDaysThreeMinutes() {
        HierarchicalTimingWheel wheel = createMarsWheel();
        Timer oneTwoThreeTimer = new MarsTimer(1, 2, 3, () -> System.out.println("timer OneTwoThree expired"));
        wheel.add(oneTwoThreeTimer);

        // advance 1 year, 2 days and 3-1 minutes
        nCopies( 1*670*1480 + 2*1480 + 3*1 -1   , (Supplier<List<Timer>>) wheel::advance)
                .stream().map(Supplier::get)
                .forEach(expiredTimers -> assertEquals(emptyList(), expiredTimers));

        // advance 1 second more and see if a timer triggers
        List<Timer> expiredTimers = wheel.advance();

        assertEquals(List.of(oneTwoThreeTimer), expiredTimers);
    }

}