import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import solution_attempts.CorrectTransactionWithCalendar;
import solution_attempts.CorrectTransactionWithEqualsGetClass;

public class EqualsCaseTest extends TestBase {
	@Before
	public void setUp() {
		test = new EqualsCase();
	}
	
	@Test
	public void successInstanceof() {
		assertTestSucceeds(CorrectTransactionWithCalendar.class);
	}
	
	@Test
	public void successGetClass() {
		assertTestSucceeds(CorrectTransactionWithEqualsGetClass.class);
	}
	
	@Test
	public void cannotInstantiate() {
		assertTestFails(Transaction.class, ObjectAccessor.CANNOT_INSTANTIATE);
	}
	
	static class Reflexive {
		public Reflexive(BigDecimal amount, Calendar date, String description) {}

		@Override
		public boolean equals(Object obj) {
			return false;
		}
	}
	@Test
	public void reflexive() {
		assertTestFails(Reflexive.class, EqualsCase.REFLEXIVE);
	}
	
	static class Symmetric {
		// A non-symmetric equals without inheritance is kind of hard to achieve, but here goes
		private final BigDecimal amount;
		private final Calendar date;
		private final String description;
		public Symmetric(BigDecimal amount, Calendar date, String description)
				{ this.amount = amount; this.date = date; this.description = description; }
		
		@Override
		public boolean equals(Object obj) {
			// First, let's fool the reflexivity test
			if (!(obj instanceof Symmetric)) {
				return false;
			}
			Symmetric other = (Symmetric)obj;
			boolean goodEquals = amount.equals(other.amount) && date.equals(other.date) && description.equals(other.description);
			if (goodEquals) { return true; }
			
			// Good, now make it asymmetric
			return hashCode() > obj.hashCode();
		}
	}
	@Test
	public void symmetic() {
		assertTestFails(Symmetric.class, EqualsCase.SYMMETRIC);
	}
	
	static class Transitive {
		private final BigDecimal amount;
		private final Calendar date;
		private final String description;
		public Transitive(BigDecimal amount, Calendar date, String description)
				{ this.amount = amount; this.date = date; this.description = description; }
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Transitive)) {
				return false;
			}
			Transitive other = (Transitive)obj;
			return amount.equals(other.amount) || date.equals(other.date) || description.equals(other.description);
		}
	}
	@Test
	public void transitive() {
		assertTestFails(Transitive.class, EqualsCase.TRANSITIVE);
	}
	
	static class NonNullTrue {
		private final BigDecimal amount;
		private final Calendar date;
		private final String description;
		public NonNullTrue(BigDecimal amount, Calendar date, String description)
				{ this.amount = amount; this.date = date; this.description = description; }
		
		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return true;
			}
			if (!(obj instanceof NonNullTrue)) {
				return false;
			}
			NonNullTrue other = (NonNullTrue)obj;
			return amount.equals(other.amount) && date.equals(other.date) && description.equals(other.description);
		}
	}
	@Test
	public void nonNullTrue() {
		assertTestFails(NonNullTrue.class, EqualsCase.NON_NULL);
	}
	
	static class NonNullNpe {
		private final BigDecimal amount;
		private final Calendar date;
		private final String description;
		public NonNullNpe(BigDecimal amount, Calendar date, String description)
				{ this.amount = amount; this.date = date; this.description = description; }
		
		@Override
		public boolean equals(Object obj) {
			if ( ! getClass().equals(obj.getClass())) {
				return false;
			}
			NonNullNpe other = (NonNullNpe)obj;
			return amount.equals(other.amount) && date.equals(other.date) && description.equals(other.description);
		}
	}
	@Test
	public void nonNullNpe() {
		assertTestFails(NonNullNpe.class, EqualsCase.NON_NULL);
	}
	
	static class AmountNotUsedWithCalendar {
		private final Calendar date;
		private final String description;
		public AmountNotUsedWithCalendar(BigDecimal amount, Calendar date, String description)
				{ this.date = date; this.description = description; }
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof AmountNotUsedWithCalendar)) {
				return false;
			}
			AmountNotUsedWithCalendar other = (AmountNotUsedWithCalendar)obj;
			return date.equals(other.date) && description.equals(other.description);
		}
	}
	@Test
	public void amountNotUsedWithCalendar() {
		assertTestFails(AmountNotUsedWithCalendar.class, EqualsCase.ALL_FIELDS);
	}
	
	static class AmountNotUsedWithDate {
		private final Date date;
		private final String description;
		public AmountNotUsedWithDate(BigDecimal amount, Date date, String description)
				{ this.date = date; this.description = description; }
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof AmountNotUsedWithDate)) {
				return false;
			}
			AmountNotUsedWithDate other = (AmountNotUsedWithDate)obj;
			return date.equals(other.date) && description.equals(other.description);
		}
	}
	@Test
	public void amountNotUsedWithDate() {
		assertTestFails(AmountNotUsedWithDate.class, EqualsCase.ALL_FIELDS);
	}
	
	static class DateAsCalendarNotUsed {
		private final BigDecimal amount;
		private final String description;
		public DateAsCalendarNotUsed(BigDecimal amount, Calendar date, String description)
				{ this.amount = amount; this.description = description; }
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof DateAsCalendarNotUsed)) {
				return false;
			}
			DateAsCalendarNotUsed other = (DateAsCalendarNotUsed)obj;
			return amount.equals(other.amount) && description.equals(other.description);
		}
	}
	@Test
	public void dateAsCalendarNotUsed() {
		assertTestFails(DateAsCalendarNotUsed.class, EqualsCase.ALL_FIELDS);
	}
	
	static class DateAsDateNotUsed {
		private final BigDecimal amount;
		private final String description;
		public DateAsDateNotUsed(BigDecimal amount, Date date, String description)
				{ this.amount = amount; this.description = description; }
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof DateAsDateNotUsed)) {
				return false;
			}
			DateAsDateNotUsed other = (DateAsDateNotUsed)obj;
			return amount.equals(other.amount) && description.equals(other.description);
		}
	}
	@Test
	public void dateAsDateNotUsed() {
		assertTestFails(DateAsDateNotUsed.class, EqualsCase.ALL_FIELDS);
	}
	
	static class DescriptionNotUsed {
		private final BigDecimal amount;
		private final Calendar date;
		public DescriptionNotUsed(BigDecimal amount, Calendar date, String description)
				{ this.amount = amount; this.date = date; }
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof DescriptionNotUsed)) {
				return false;
			}
			DescriptionNotUsed other = (DescriptionNotUsed)obj;
			return amount.equals(other.amount) && date.equals(other.date);
		}
	}
	@Test
	public void descriptionNotUsed() {
		assertTestFails(DescriptionNotUsed.class, EqualsCase.ALL_FIELDS);
	}
	
	static class NonFinal {
		private final BigDecimal amount;
		private final Calendar date;
		private final String description;
		public NonFinal(BigDecimal amount, Calendar date, String description)
				{ this.amount = amount; this.date = date; this.description = description; }
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof NonFinal)) {
				return false;
			}
			NonFinal other = (NonFinal)obj;
			return amount.equals(other.amount) && date.equals(other.date) && description.equals(other.description);
		}
	}
	@Test
	public void nonFinal() {
		assertTestFails(NonFinal.class, EqualsCase.NON_FINAL);
	}
	
	static final class ClassFinal {
		private final BigDecimal amount;
		private final Calendar date;
		private final String description;
		public ClassFinal(BigDecimal amount, Calendar date, String description)
				{ this.amount = amount; this.date = date; this.description = description; }
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof ClassFinal)) {
				return false;
			}
			ClassFinal other = (ClassFinal)obj;
			return amount.equals(other.amount) && date.equals(other.date) && description.equals(other.description);
		}
	}
	@Test
	public void classIsFinal() {
		assertTestSucceeds(ClassFinal.class);
	}
	
	static class EqualsFinal {
		private final BigDecimal amount;
		private final Calendar date;
		private final String description;
		public EqualsFinal(BigDecimal amount, Calendar date, String description)
				{ this.amount = amount; this.date = date; this.description = description; }
		
		@Override
		public final boolean equals(Object obj) {
			if (!(obj instanceof EqualsFinal)) {
				return false;
			}
			EqualsFinal other = (EqualsFinal)obj;
			return amount.equals(other.amount) && date.equals(other.date) && description.equals(other.description);
		}
	}
	@Test
	public void equalsIsFinal() {
		assertTestSucceeds(EqualsFinal.class);
	}
}
