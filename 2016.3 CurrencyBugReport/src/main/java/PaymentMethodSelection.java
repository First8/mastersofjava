
import java.util.Currency;

/**
 * Payment Method Selection
 */
public class PaymentMethodSelection {

    private final double value;
    private final Currency currency;

    public PaymentMethodSelection(double value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public double getActualPaidValue() {
        return value;
    }

    public Currency getActualPaidCurrency() {
        return currency;
    }
}
