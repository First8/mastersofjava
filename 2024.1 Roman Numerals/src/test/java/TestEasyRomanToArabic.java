import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestEasyRomanToArabic {

    RomanInterpreter romanInterpreter = new RomanInterpreter();
    @Test
    public void testItoInt() {

        int actual = romanInterpreter.romanToArabic("I");
        int expected = 1;

        assertEquals(expected, actual);
    }

    @Test
    public void testVtoInt() {

        int actual = romanInterpreter.romanToArabic("V");
        int expected = 5;

        assertEquals(expected, actual);
    }
    @Test
    public void testIIItoInt() {

        int actual = romanInterpreter.romanToArabic("III");
        int expected = 3;

        assertEquals(expected, actual);
    }

    @Test
    public void testXXItoInt() {

        int actual = romanInterpreter.romanToArabic("XXI");
        int expected = 21;

        assertEquals(expected, actual);
    }

    @Test
    public void testXXVIIItoInt() {

        int actual = romanInterpreter.romanToArabic("XXVIII");
        int expected = 28;

        assertEquals(expected, actual);
    }

    @Test
    public void testLVIIItoInt() {

        int actual = romanInterpreter.romanToArabic("LVIII");
        int expected = 58;

        assertEquals(expected, actual);
    }

    @Test
    public void testLXVtoInt() {

        int actual = romanInterpreter.romanToArabic("LXV");
        int expected = 65;

        assertEquals(expected, actual);
    }

    @Test
    public void testLXVItoInt() {

        int actual = romanInterpreter.romanToArabic("LXVI");
        int expected = 66;

        assertEquals(expected, actual);
    }


    @Test
    public void testLXXXVIIItoInt() {

        int actual = romanInterpreter.romanToArabic("LXXXVIII");
        int expected = 88;

        assertEquals(expected, actual);
    }

}
