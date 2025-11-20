import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBelowMinus10 {
    @Test
    public void toRadix() {
        var converter = new Converter();

        assertEquals("1z0", converter.toRadix(36, -36));
        assertEquals("1q", converter.toRadix(-10, -36));

        assertEquals("1508", converter.toRadix(-1000, -12));
        assertEquals("1k2c", converter.toRadix(-1000, -22));
        assertEquals("s8", converter.toRadix(-1000, -36));

        assertEquals("3a0", converter.toRadix(1000, -20));
        assertEquals("2lk", converter.toRadix(1000, -28));
        assertEquals("19s", converter.toRadix(1000, -36));
    }
}
