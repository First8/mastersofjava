
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertArrayEquals;

public class HiddenTest {
    @Test
    public void divide_10_by_8_US() {
        assertArrayEquals(createDisplay("1.25"), PokemonCalculator.divideWithLocale(10, 8, Locale.US));
    }

    @Test
    public void divide_10_by_3_DE(){
        assertArrayEquals(createDisplay("??????????"), PokemonCalculator.divideWithLocale(10, 3, Locale.GERMAN));
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