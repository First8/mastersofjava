import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HiddenTest {

    RomanInterpreter romanParser = new RomanInterpreter();

    @Test
    public void testComplexMultipleRomanNumerals() {
        assertEquals(25, romanParser.romanToArabic("XXV"));
        assertEquals(59, romanParser.romanToArabic("LIX"));
        assertEquals(190, romanParser.romanToArabic("CXC"));
        assertEquals(899, romanParser.romanToArabic("DCCCXCIX"));
        assertEquals(947, romanParser.romanToArabic("CMXLVII"));
        assertEquals(1994, romanParser.romanToArabic("MCMXCIV"));
    }

}
