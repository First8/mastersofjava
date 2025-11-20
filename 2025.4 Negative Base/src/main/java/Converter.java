/**
 * This class converts numbers from base 10 to a given (negative) base.
 */
public class Converter {
    String characters = "0123456789abcdefghijklmnopqrstuvwxyz";

    public char toChar(int digit)  {
        return characters.charAt(digit);
    }

    public String toRadix(int number, int base) {
        return Integer.toString(number, base);
    }
}
