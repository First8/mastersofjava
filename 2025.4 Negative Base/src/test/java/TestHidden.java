import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHidden {
    @Test
    public void toRadix() {
        var converter = new Converter();
        assertEquals("1100100", converter.toRadix(36, -2));
        assertEquals("110001000", converter.toRadix(120, -2));

        assertEquals("250", converter.toRadix(42, -6));

        assertEquals("1621", converter.toRadix(-143, -8));

        assertEquals("28", converter.toRadix(-12, -10));
        assertEquals("259", converter.toRadix(159, -10));

        assertEquals("1h7cd", converter.toRadix(7897, -18));
        assertEquals("5a", converter.toRadix(-100, -22));
        assertEquals("z", converter.toRadix(35, -36));
    }
}
