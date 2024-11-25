import org.junit.Test;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestTimer {


    @Test
    public void testGetSeconds() {
        DhmsTimer testTimer = new DhmsTimer(Duration.ofSeconds(119), () -> {});
        assertEquals(59, testTimer.getSeconds());
    }

    @Test
    public void testGetMinutes() {
        DhmsTimer testTimer = new DhmsTimer(Duration.ofSeconds(119), () -> {});
        assertEquals(1, testTimer.getMinutes());
    }

    @Test
    public void testCallExpiryMethod() {
        AtomicBoolean called = new AtomicBoolean(false);
        DhmsTimer testTimer = new DhmsTimer(Duration.ofSeconds(119), () -> called.set(true));

        testTimer.callExpiryMethod();

        assertTrue(called.get());
    }
}
