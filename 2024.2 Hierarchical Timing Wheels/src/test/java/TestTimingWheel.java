import org.junit.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestTimingWheel {

    @Test
    public void testTwoSeconds() {
        DhmsTimer twoSecondTimer = new DhmsTimer (Duration.ofSeconds(2), ()-> {});
        TimingWheel timingWheel = new TimingWheel(60);
        timingWheel.insert(twoSecondTimer, twoSecondTimer.getSeconds());

        List<Timer> oneSecondTimers = timingWheel.advance();
        assertEquals(oneSecondTimers, Collections.emptyList());

        List<Timer> twoSecondTimers = timingWheel.advance();
        assertEquals(twoSecondTimers, List.of(twoSecondTimer));
    }
}
