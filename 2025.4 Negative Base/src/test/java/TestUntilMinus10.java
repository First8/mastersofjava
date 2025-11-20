import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestUntilMinus10 {
    @Test
    public void toRadix() {
        var converter = new Converter();
        assertEquals("1", converter.toRadix(1, -2));
        assertEquals("110", converter.toRadix(2, -2));
        assertEquals("111", converter.toRadix(3, -2));
        assertEquals("1101", converter.toRadix(-3, -2));
        assertEquals("11010", converter.toRadix(6, -2));
        assertEquals("11100", converter.toRadix(12, -2));
        assertEquals("11010001", converter.toRadix(-47, -2));
        assertEquals("111011010", converter.toRadix(198, -2));

        assertEquals("122", converter.toRadix(10, -4));
        assertEquals("181", converter.toRadix(10, -9));

        assertEquals("28", converter.toRadix(-12, -10));
        assertEquals("191", converter.toRadix(11, -10));
    }
}
