

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

/**
 * Certification test. 
 */
public class ProvidedUnitTest {
    public static final Random RND = new Random();
    NumberFormat nf = new DecimalFormat("########0.00");
   
    @Test
    public void statusTransaction() {
       
        for (int i=0; i<5; i++) {
            int value = RND.nextInt(100000);
           
            TransactionImpl t = Gateway.createTransaction(value, "EUR");
            String status = Gateway.getStatus(t);
            
            System.out.println("testing status with: " + value + " - " +status);
            Assert.assertTrue( status.equals("To be paid: EUR " + nf.format(value/100.0d)) );
        }
    }
   
    @Test
    public void statusAndPayTransaction() {

        for (int i=0; i<5; i++) {
            int value = RND.nextInt(100000);
            
            TransactionImpl t = Gateway.createTransaction(value, "EUR");
            t = Gateway.payTransaction(t, new PaymentMethodSelection(value, Currency.getInstance("EUR")));
            String status = Gateway.getStatus(t);
            
            System.out.println("testing payment with: " + value+ " - " +status);
            Assert.assertTrue( status.equals("Paid: EUR " + nf.format(value/100.0d)) );
        }
    }

    /**
     * FIXME: broken unit test. Goal: modify a transaction from EUR to USD, pay the amount in USD
     */
    @Test
    public void payAndModifyTransaction() {

        for (int i=0; i<5; i++) {
            int value = RND.nextInt(100000);
            int valueExchanged = (int)Math.round(ExchangeRateDao.convert(value, null, null));
            
            TransactionImpl t = Gateway.createTransaction(value, "EUR");
            Gateway.modifyCurrency(t, "USD");
            PaymentMethodSelection pms = new PaymentMethodSelection(valueExchanged, Currency.getInstance("USD"));
            
            t = Gateway.payTransaction(t, pms);
            String status = Gateway.getStatus(t);
            
            System.out.println("testing modify with: " + value + " and " + valueExchanged + " - "+status);
            Assert.assertTrue( status.equals("Paid: USD " + nf.format(valueExchanged/100.0d)) );
        }
    }

}