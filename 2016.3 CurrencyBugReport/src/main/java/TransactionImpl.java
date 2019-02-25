
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;

/**
 * Modify this class to ensure valid transactions for various currencies. 
 */
public class TransactionImpl {
    
    private double value; 
    private Currency currency;
    private State state;

    public TransactionImpl(double value, Currency currency) {
        this.value = value;
        this.currency = currency;
        this.state  = State.NEW;
    }
    
    public double getValue() { 
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    
    public String toString() {
        NumberFormat nf = new DecimalFormat("########0.00");
        return currency.toString() 
                + " " + nf.format(value/100); // in cents
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}