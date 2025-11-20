/**
 * This class converts numbers from base 10 to a given (negative) base.
 */
public class Converter {

    String characters = "0123456789abcdefghijklmnopqrstuvwxyz";

    public String toRadix(int number, int base) {
        if (number == 0)
            return "0";

        String converted = "";
        while (number != 0) {
            long remainder = number % base;
            number /= base;

            if (remainder < 0) {
                remainder += (-base);
                number += 1;
            }

            // convert remainder to String add into the result
            converted = characters.charAt((int) remainder) + converted;
        }
        return converted;
    }
}
