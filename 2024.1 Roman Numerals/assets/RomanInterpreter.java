import java.util.*;

public class RomanInterpreter {

    int value(char r) {
        return switch (r){
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
        int total = 0;
        for (int i = 0; i < input.length(); i++) {
            int s1 = value(input.charAt(i));
            if (i + 1 < input.length()) {
                int s2 = value(input.charAt(i + 1));
                if (s1 >= s2) {
                    total = total + s1;
                } else {
                    total = total - s1;
                }
            } else {
                total = total + s1;
            }
        }
        return total;
    }

}