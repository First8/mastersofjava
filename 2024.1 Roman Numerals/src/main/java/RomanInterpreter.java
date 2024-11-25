import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class RomanInterpreter {

    // Returns the value of a Roman numeral
    int value(char r) {
        return switch (r) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> -1;
        };
    }

    public int romanToArabic(String input) {
        return 0;
    }

}
