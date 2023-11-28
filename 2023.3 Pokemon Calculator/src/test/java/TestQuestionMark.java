

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertArrayEquals;

public class TestQuestionMark {
	
	
    @Test
    public void divide_7_by_3_DE() {
        assertArrayEquals(createDisplay("??????????"), PokemonCalculator.divideWithLocale(7, 3, Locale.GERMAN));
    }

    @Test
    public void divide_1_by_7_DE() {
        assertArrayEquals(createDisplay("??????????"), PokemonCalculator.divideWithLocale(1, 7, Locale.GERMAN));
    }

    static DisplayNumber[] createDisplay(String value) {
        DisplayNumber[] values = new DisplayNumber[10];
        int totalPositionsFrom0 = value.length() -1;
        for (int i = 0; i <= totalPositionsFrom0; i++) {
            values[ totalPositionsFrom0 - i] = new DisplayNumber(value.charAt(i));
        }
        return values;
    }
}