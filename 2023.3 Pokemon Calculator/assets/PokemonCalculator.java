import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class PokemonCalculator {

    public static final int MAX_DISPLAY_CHARS = 10;

    public static DisplayNumber[] divideWithLocale(double dividend, double divisor, Locale locale)  {

//        return divideWithLocaleSolution1(dividend, divisor, locale); // Solution 1
        return divideWithLocaleSolution2(dividend, divisor, locale); // Solution 2
    }

    /**
     * Solution 2, exact replication of the bug itself using the locale issue
     */
    public static DisplayNumber[] divideWithLocaleSolution2(double dividend, double divisor, Locale locale)  {
        DisplayNumber[] displayValues = new DisplayNumber[MAX_DISPLAY_CHARS];

        try {

            double value = dividend / divisor;

            DecimalFormat decimalFormat = getDecimalFormat(locale);
            String formattedValue = decimalFormat.format(value);

            BigDecimal parsedDecimal = (BigDecimal) decimalFormat.parse(formattedValue);


            int separatorIndex = formattedValue.indexOf('.');
            String integerPart = separatorIndex > 0 ? formattedValue.substring(0, separatorIndex) : formattedValue;
            String decimalPart = separatorIndex > 0 ? formattedValue.substring(separatorIndex + 1) : "";

            int integerOffset = 0;

            if(decimalPart.length() + integerPart.length() > 10 ) {
                if (formattedValue.contains(",")) {
                    return createDisplay("??????????");
                } else {
                    decimalPart = decimalPart.substring(0, 9 - integerPart.length());
                }
            }

            if (decimalPart.length() != 0 || separatorIndex > -1) {
                integerOffset = decimalPart.length() + 1;

                int dotIndex = decimalPart.length();
                if (dotIndex >= MAX_DISPLAY_CHARS) {
                    dotIndex = integerPart.length();
                }
                displayValues[dotIndex] = new DisplayNumber('.');
            }
            setDisplayValues(parsedDecimal.longValue(), integerPart.length(), integerOffset, displayValues);

            if (decimalPart.length() > 0) {
                setDisplayValues(Long.parseLong(decimalPart), decimalPart.length(), 0, displayValues);
            }

            return displayValues;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return displayValues;
    }

    private static DecimalFormat getDecimalFormat(Locale locale) {
        // Create a number format for the European locale
        NumberFormat nf = NumberFormat.getNumberInstance(locale);

        // Cast the number format to a decimal format
        DecimalFormat df = (DecimalFormat) nf;
        df.setMaximumFractionDigits(MAX_DISPLAY_CHARS); // -2 due to the decimal point
        df.setParseBigDecimal(true);
        return df;
    }

    private static DisplayNumber[] setDisplayValues(long number, int digitLength, int integerOffset, DisplayNumber[] displayNumbers) {
        if (digitLength > 0) {
            for (int place = 0; place < digitLength; place++) {
                double powTen = Math.pow(10, place);
                int placeIndex = integerOffset + place;

                int nextPowTen = (int) powTen * 10;

                int newNumber = 0;
                if (nextPowTen != 0) {
                    newNumber = (int) (number / nextPowTen);
                }

                int remainder = 0;

                if (powTen != 0) {
                    remainder = (int) ((number - newNumber * nextPowTen) / powTen);
                }
                displayNumbers[placeIndex] = new DisplayNumber(Character.forDigit(remainder, 10));
            }
        }

        return displayNumbers;
    }


    /**
     * Solution 1, just programming the behaviour that is specified
     */
    private static DisplayNumber[] divideWithLocaleSolution1(double dividend, double divisor, Locale locale) {
        DecimalFormat df = getDecimalFormat(locale);

        double quotientDouble = dividend / divisor;

        BigDecimal bigDecimal = new BigDecimal(quotientDouble);

        String responseDouble = bigDecimal.toString();
        char decimalSeparator = df.getDecimalFormatSymbols().getDecimalSeparator();
        if (responseDouble.length() > MAX_DISPLAY_CHARS && decimalSeparator == ','){
            // if the decimal separator is a comma, we will display all with leading 0. But we are only able to show a maximum of 10 characters. If above, we return ??????????.
            return createDisplay("??????????");
        }

        if (responseDouble.length() > MAX_DISPLAY_CHARS) {
            responseDouble = responseDouble.substring(0, MAX_DISPLAY_CHARS);

        }
        if (decimalSeparator == '.') {
            return createDisplay(responseDouble);
        }

        String integerCalculationResultString = bigDecimal.toBigInteger().toString(); // Pad with leading zeros to ensure 3-character response

        int leftPadNumber = responseDouble.length() - integerCalculationResultString.length();

        String value = "0".repeat(Math.max(0, leftPadNumber)) +
                integerCalculationResultString;
        return createDisplay(value);
    }

    private static DisplayNumber[] createDisplay(String value) {
        DisplayNumber[] values = new DisplayNumber[MAX_DISPLAY_CHARS];
        for (int i = 0; i < value.length(); i++) {
            values[value.length() - i - 1] = new DisplayNumber(value.charAt(i));
        }
        return values;
    }
}