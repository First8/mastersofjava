import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestComplexRomanToArabic {

    RomanInterpreter romanInterpreter = new RomanInterpreter();

    @Test
    public void testCMXCIXtoInt() {

        int actual = romanInterpreter.romanToArabic("CMXCIX");
        int expected = 999;

        assertEquals(expected, actual);
    }

    @Test
    public void testMCCCLXXXVIIItoInt() {

        int actual = romanInterpreter.romanToArabic("MCCCLXXXVIII");
        int expected = 1388;

        assertEquals(expected, actual);
    }

    @Test
    public void testMMCMXLIXtoInt() {

        int actual = romanInterpreter.romanToArabic("MMCMXLIX");
        int expected = 2949;

        assertEquals(expected, actual);
    }

    @Test
    public void testMMCMXCIVtoInt() {

        int actual = romanInterpreter.romanToArabic("MMCMXCIV");
        int expected = 2994;

        assertEquals(expected, actual);
    }

    @Test
    public void testMMCMXCIXtoInt() {

        int actual = romanInterpreter.romanToArabic("MMCMXCIX");
        int expected = 2999;

        assertEquals(expected, actual);
    }

    @Test
    public void testMMMDCCCXCIXtoInt() {

        int actual = romanInterpreter.romanToArabic("MMMDCCCXCIX");
        int expected = 3899;

        assertEquals(expected, actual);
    }

    @Test
    public void testMMMCMXCIVtoInt() {

        int actual = romanInterpreter.romanToArabic("MMMCMXCIV");
        int expected = 3994;

        assertEquals(expected, actual);
    }
}
