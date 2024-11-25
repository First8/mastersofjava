import org.junit.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import static java.util.Collections.emptyList;
import static java.util.Collections.nCopies;
import static org.junit.Assert.assertEquals;

public class HierarchicalTimingWheelTest {

    /**
     * Creates a hierarchical timing wheel with days, hours, minutes and seconds wheels, expecting a DhmsTimer.
     * @return a wheel
     */
    private HierarchicalTimingWheel createHierarchicalTimingWheel() {
        HierarchicalTimingWheel smallestWheel =
                new HierarchicalTimingWheel(60, t -> ((DhmsTimer) t).getSeconds() );
        smallestWheel.addBiggerWheel(60, t -> ((DhmsTimer) t).getMinutes())
                .addBiggerWheel(24, t -> ((DhmsTimer) t).getHours())
                .addBiggerWheel(100, t -> ((DhmsTimer) t).getDays());
        return smallestWheel;
    }


    @Test
    public void testTwoSeconds() {
        HierarchicalTimingWheel hierarchicalWheel = createHierarchicalTimingWheel();

        Timer twoSecondTimer = new DhmsTimer(Duration.ofSeconds(2), () -> System.out.println("timer TwoSeconds expired"));
        hierarchicalWheel.add(twoSecondTimer);

        List<Timer> expiredTimers = hierarchicalWheel.advance();
        assertEquals(emptyList(), expiredTimers);

        expiredTimers = hierarchicalWheel.advance();
        assertEquals(List.of(twoSecondTimer), expiredTimers);
    }


    @Test
    public void testOneMinuteTwoSeconds() {
        HierarchicalTimingWheel hierarchicalWheel = createHierarchicalTimingWheel();
        Timer testTimer = new DhmsTimer(Duration.ofSeconds(62), () -> System.out.println("timer OneMinuteTwoSeconds expired"));
        hierarchicalWheel.add(testTimer);

        // advance 61 seconds and see if no timers trigger
        nCopies(61, (Supplier<List<Timer>>) hierarchicalWheel::advance)
                .stream().map(Supplier::get)
                .forEach(expiredTimers -> assertEquals(emptyList(), expiredTimers));

        // advance 1 second more and see if a timer triggers
        List<Timer> expiredTimers = hierarchicalWheel.advance();

        assertEquals(List.of(testTimer), expiredTimers);
    }

    @Test
    public void testRandomTimer() {
        HierarchicalTimingWheel hierarchicalWheel = createHierarchicalTimingWheel();
        int seconds = new Random().nextInt(8640000);
        Timer randomTimer = new DhmsTimer(Duration.ofSeconds(seconds), () -> System.out.println("randomTimer expired"));
        hierarchicalWheel.add(randomTimer);

        nCopies(seconds - 1, (Supplier<List<Timer>>) hierarchicalWheel::advance)
                .stream().map(Supplier::get)
                .forEach(expiredTimers -> assertEquals(emptyList(), expiredTimers));

        List<Timer> expiredTimers = hierarchicalWheel.advance();

        assertEquals(List.of(randomTimer), expiredTimers);
    }
}