
import java.util.Currency;

/**
 * Gateway class with main actions on transactions.
 */
public class Gateway {
    
    public static String getStatus(TransactionImpl t) {
        return (t.getState()==State.NEW?"To be paid: ":"Paid: ") + t.toString();
    }

    public static TransactionImpl createTransaction(long value, String currency) {
        return  new TransactionImpl( value, Currency.getInstance(currency));
    }

    public static TransactionImpl payTransaction(TransactionImpl t, PaymentMethodSelection pms) {
    	boolean isRightAmount = pms.getActualPaidCurrency().equals(t.getCurrency()) && Math.abs(pms.getActualPaidValue()-t.getValue())<0.01d;
        if (isRightAmount) {
            t.setValue(pms.getActualPaidValue()); 
            t.setCurrency(pms.getActualPaidCurrency());
            t.setState(State.PAID);
            return t;
        } else {
            throw new IllegalArgumentException("Wrong amount paid");
        }
    }

    public static void modifyCurrency(TransactionImpl t, String currency) {
        Currency newCurrency = Currency.getInstance(currency);
        System.out.println("original: " + t.getValue());
        double exchangedValue = ExchangeRateDao.convert((double)t.getValue(), t.getCurrency(), newCurrency);
        long roundedAmount =  Math.round(exchangedValue);
        long cents = roundedAmount * 100;
        System.out.println("converted: " + exchangedValue);
        System.out.println("rounded: " + roundedAmount);
        t.setValue( cents );
        t.setCurrency( newCurrency);
    }

}