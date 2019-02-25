

import java.util.Currency;

/**
 * Example Exchange Rate service: a very solid banking system! 
 */
public class ExchangeRateDao {

    public static double getRate(Currency from, Currency to) {
        return 1.1d;
    }

    public static double convert(double value, Currency from, Currency to) {
        return value * getRate(from,to);
    }

}
