import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import solution_attempts.CorrectTransactionWithCalendar;
import solution_attempts.CorrectTransactionWithDate;

public class CompareToCaseTest extends TestBase {
	
	@Before
	public void setUp() {
		test = new CompareToCase();
	}
	
	@Test
	public void successWithCalendar() {
		assertTestSucceeds(CorrectTransactionWithCalendar.class);
	}
	
	@Test
	public void successWithDate() {
		assertTestSucceeds(CorrectTransactionWithDate.class);
	}
	
	@Test
	public void cannotInstantiate() {
		assertTestFails(Transaction.class, ObjectAccessor.CANNOT_INSTANTIATE);
	}
	
	static class TooManyInterfaces implements Comparable<TooManyInterfaces>, Cloneable {
		public TooManyInterfaces(BigDecimal amount, Calendar date, String description) {}
		@Override public int compareTo(TooManyInterfaces o) { return 0; }
	}
	@Test
	public void tooManyInterfaec() {
		assertTestFails(TooManyInterfaces.class, CompareToCase.TOO_MANY_INTERFACES);
	}
	
	static class ImplementsSomethingElse implements Cloneable {
		public ImplementsSomethingElse(BigDecimal amount, Calendar date, String description) {}
	}
	@Test
	public void implementsSomethingElse() {
		assertTestFails(ImplementsSomethingElse.class, CompareToCase.DOES_NOT_IMPLEMENT_INTERFACE);
	}
	
	static class DoesNotImplementInterface {
		public DoesNotImplementInterface(BigDecimal amount, Calendar date, String description) {}
	}
	@Test
	public void implementsInterface() {
		assertTestFails(DoesNotImplementInterface.class, CompareToCase.DOES_NOT_IMPLEMENT_INTERFACE);
	}
	
	static class WrongGenericParameter implements Comparable<String> {
		public WrongGenericParameter(BigDecimal amount, Calendar date, String description) {}
		@Override public int compareTo(String o) { return 0; }
	}
	@Test
	public void wrongGenericParameter() {
		assertTestFails(WrongGenericParameter.class, CompareToCase.WRONG_GENERIC_PARAMETER);
	}
	
	@SuppressWarnings("rawtypes")
	static class NoGenericParameter implements Comparable {
		public NoGenericParameter(BigDecimal amount, Calendar date, String description) {}
		@Override
		public int compareTo(Object o) {
			return 0;
		}
	}
	@Test
	public void noGenericParameter() {
		assertTestFails(NoGenericParameter.class, CompareToCase.WRONG_GENERIC_PARAMETER);
	}

	static class NoOrder implements Comparable<NoOrder> {
		public NoOrder(BigDecimal amount, Date date, String description) {}

		@Override
		public int compareTo(NoOrder o) {
			return 0;
		}
	}
	@Test
	public void noOrder() {
		assertTestFails(NoOrder.class, CompareToCase.WRONG_ORDER);
	}

	static class AscendingOrderWithCalendar implements Comparable<AscendingOrderWithCalendar> {
		private final Calendar date;
		public AscendingOrderWithCalendar(BigDecimal amount, Calendar date, String description) { this.date = date; }

		@Override
		public int compareTo(AscendingOrderWithCalendar o) {
			return date.compareTo(o.date);
		}
	}
	@Test
	public void orderingWithCalendar() {
		assertTestFails(AscendingOrderWithDate.class, CompareToCase.WRONG_ORDER);
	}

	static class AscendingOrderWithDate implements Comparable<AscendingOrderWithDate> {
		private final Date date;
		public AscendingOrderWithDate(BigDecimal amount, Date date, String description) { this.date = date; }

		@Override
		public int compareTo(AscendingOrderWithDate o) {
			return date.compareTo(o.date);
		}
	}
	@Test
	public void orderingWithDate() {
		assertTestFails(AscendingOrderWithDate.class, CompareToCase.WRONG_ORDER);
	}

	static class InconsistentWithEqualsOnlyAmount implements Comparable<InconsistentWithEqualsOnlyAmount> {
		private final BigDecimal amount;
		private final Calendar date;
		private final String description;
		public InconsistentWithEqualsOnlyAmount(BigDecimal amount, Calendar date, String description)
				{ this.amount = amount; this.date = date; this.description = description; }
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof InconsistentWithEqualsOnlyAmount)) {
				return false;
			}
			InconsistentWithEqualsOnlyAmount other = (InconsistentWithEqualsOnlyAmount)obj;
			return amount.equals(other.amount) && date.equals(other.date) && description.equals(other.description);
		}
		
		@Override
		public int compareTo(InconsistentWithEqualsOnlyAmount obj) {
			int dateDiff = obj.date.compareTo(date);
			if (dateDiff != 0) {
				return dateDiff;
			}
			return description.compareTo(obj.description);
		}
	}
	@Test
	public void inconsistentWithEqualsOnlyAmount() {
		assertTestFails(InconsistentWithEqualsOnlyAmount.class, CompareToCase.CONSISTENT_WITH_EQUALS);
	}

	static class InconsistentWithEqualsOnlyDescription implements Comparable<InconsistentWithEqualsOnlyDescription> {
		private final BigDecimal amount;
		private final Calendar date;
		private final String description;
		public InconsistentWithEqualsOnlyDescription(BigDecimal amount, Calendar date, String description)
				{ this.amount = amount; this.date = date; this.description = description; }
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof InconsistentWithEqualsOnlyDescription)) {
				return false;
			}
			InconsistentWithEqualsOnlyDescription other = (InconsistentWithEqualsOnlyDescription)obj;
			return amount.equals(other.amount) && date.equals(other.date) && description.equals(other.description);
		}
		
		@Override
		public int compareTo(InconsistentWithEqualsOnlyDescription obj) {
			int dateDiff = obj.date.compareTo(date);
			if (dateDiff != 0) {
				return dateDiff;
			}
			return description.compareTo(obj.description);
		}
	}
	@Test
	public void inconsistentWithEqualsOnlyDescription() {
		assertTestFails(InconsistentWithEqualsOnlyDescription.class, CompareToCase.CONSISTENT_WITH_EQUALS);
	}

	static class InconsistentWithEqualsAllFields implements Comparable<InconsistentWithEqualsAllFields> {
		private final BigDecimal amount;
		private final Calendar date;
		private final String description;
		public InconsistentWithEqualsAllFields(BigDecimal amount, Calendar date, String description)
				{ this.amount = amount; this.date = date; this.description = description; }
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof InconsistentWithEqualsAllFields)) {
				return false;
			}
			InconsistentWithEqualsAllFields other = (InconsistentWithEqualsAllFields)obj;
			return amount.equals(other.amount) && date.equals(other.date) && description.equals(other.description);
		}
		
		@Override
		public int compareTo(InconsistentWithEqualsAllFields obj) {
			return obj.date.compareTo(date);
		}
	}
	@Test
	public void inconsistentWithEqualsAllFields() {
		assertTestFails(InconsistentWithEqualsAllFields.class, CompareToCase.CONSISTENT_WITH_EQUALS);
	}
}
