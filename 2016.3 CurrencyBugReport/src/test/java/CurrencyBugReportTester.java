import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.function.Supplier;

import nl.moj.model.Tester;

public class CurrencyBugReportTester implements Tester.Testable {
	
	private final CertificationTest[] TRANSACTION_TESTS = new CertificationTest[3];

	public CurrencyBugReportTester() {
		
		TRANSACTION_TESTS[0]= new CertificationTest() {
			
			@Override
			public String getName() {
				return "status Transaction";
			}
			
			@Override
			public String getDescription() {
				return "Tests status of a transaction";
			}
			
			@Override
			public Boolean get() {
			   TransactionImpl t = Gateway.createTransaction(990, "EUR");
		        String status = Gateway.getStatus(t);
		        
		        return status.equals("To be paid: EUR 9,90");
			}
		};
		
		TRANSACTION_TESTS[1]=new CertificationTest() { 
			
			@Override
			public String getName() {
				return "pay Transaction";
			}
			
			@Override
			public String getDescription() {
				return "Tests payment of a transaction";
			}
			

			@Override
		    public Boolean get() {
		        TransactionImpl t = Gateway.createTransaction(990, "EUR");
		        t = Gateway.payTransaction(t, new PaymentMethodSelection(990, Currency.getInstance("EUR")));
		        String status = Gateway.getStatus(t);
		        
		        return status.equals("Paid: EUR 9,90");
		    }
		};
		
		TRANSACTION_TESTS[2]=new CertificationTest() { 
			
			@Override
			public String getName() {
				return "modify Transaction";
			}
			
			@Override
			public String getDescription() {
				return "Tests currency modification and payment of a transaction";
			}
			
			@Override
		    public Boolean get() {
				TransactionImpl t = Gateway.createTransaction(990, "EUR");
				Gateway.modifyCurrency(t, "USD");
				System.out.println(t.getValue());
				t = Gateway.payTransaction(t, new PaymentMethodSelection(1089, Currency.getInstance("USD")));
				String status = Gateway.getStatus(t);
				return status.equals("Paid: USD 10,89");
		    }
		};
	}
	

	@Override
	public int getTestCount() {
		return TRANSACTION_TESTS.length;
	}
	
	@Override
	public String getTestName(int nr) {
		return TRANSACTION_TESTS[nr].getName();
	}
	
	@Override
	public String getTestDescription(int nr) {
		return TRANSACTION_TESTS[nr].getDescription();
	}
	
	@Override
	public boolean performTest(int nr) throws Throwable {
		return TRANSACTION_TESTS[nr].get();
	}
	
	public void executeAll() {
		
		for (int index=0;index< this.getTestCount();index++) {
			try {
				System.out.println("ct " +getTestDescription(index) + " " +getTestName(index) + " " + performTest(index)); 

			} catch (Throwable ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public interface CertificationTest extends Supplier<Boolean> {

		public String getName();

		public String getDescription();
		
		
	}

	public static void main(String[] args) {
		CurrencyBugReportTester instance = new CurrencyBugReportTester(); 
		instance.executeAll();
	}
}
