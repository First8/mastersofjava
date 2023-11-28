

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertArrayEquals;

public class PokemonCalculatorTest {
    @Test
    public void divide_10_by_4_US() {
        assertArrayEquals(createDisplay("2.5"), PokemonCalculator.divideWithLocale(10, 4, Locale.US));
    }

    @Test
    public void divide_10_by_4_DE() {
        assertArrayEquals(createDisplay("002"), PokemonCalculator.divideWithLocale(10, 4, Locale.GERMAN));
    }

    @Test
    public void divide_10_by_8_US() {
        assertArrayEquals(createDisplay("1.25"), PokemonCalculator.divideWithLocale(10, 8, Locale.US));
    }
    @Test
    public void divide_10_by_8_DE() {
        assertArrayEquals(createDisplay("0001"), PokemonCalculator.divideWithLocale(10, 8, Locale.GERMAN));
    }

    @Test
    public void divide_100_by_8_US() {
        assertArrayEquals(createDisplay("12.5"), PokemonCalculator.divideWithLocale(100, 8, Locale.US));
    }
    @Test
    public void divide_100_by_8_DE() {
        assertArrayEquals(createDisplay("0012"), PokemonCalculator.divideWithLocale(100, 8, Locale.GERMAN));
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