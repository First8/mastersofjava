import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class HashCodeCaseTest extends TestBase {
	@Before
	public void setUp() {
		test = new HashCodeCase();
	}
	
	@Test
	public void cannotInstantiate() {
		assertTestFails(Transaction.class, ObjectAccessor.CANNOT_INSTANTIATE);
	}

	@SuppressWarnings("unused")
	static class HashCodeNotOverridden {
		private final BigDecimal amount;
		private final Calendar date;
		private final String description;
		public HashCodeNotOverridden(BigDecimal amount, Calendar date, String description)
				{ this.amount = amount; this.date = date; this.description = description; }
	}
	@Test
	public void hashCodeNotOverridden() {
		assertTestFails(HashCodeNotOverridden.class, HashCodeCase.HASHCODE_NOT_OVERRIDDEN);
	}

	static class AmountNotUsedWithCalendar {
		private final Calendar date;
		private final String description;
		public AmountNotUsedWithCalendar(BigDecimal amount, Calendar date, String description)
				{ this.date = date; this.description = description; }
		
		@Override
		public int hashCode() {
			int result = 0;
			result = result + (59 * date.hashCode());
			result = result + (59 * description.hashCode());
			return result;
		}
	}
	@Test
	public void amountNotUsedWithCalendar() {
		assertTestFails(AmountNotUsedWithCalendar.class, HashCodeCase.ALL_FIELDS);
	}
	
	static class AmountNotUsedWithDate {
		private final Date date;
		private final String description;
		public AmountNotUsedWithDate(BigDecimal amount, Date date, String description)
				{ this.date = date; this.description = description; }
		
		@Override
		public int hashCode() {
			int result = 0;
			result = result + (59 * date.hashCode());
			result = result + (59 * description.hashCode());
			return result;
		}
	}
	@Test
	public void amountNotUsedWithDate() {
		assertTestFails(AmountNotUsedWithDate.class, HashCodeCase.ALL_FIELDS);
	}
	
	static class DateAsCalendarNotUsed {
		private final BigDecimal amount;
		private final String description;
		public DateAsCalendarNotUsed(BigDecimal amount, Calendar date, String description)
				{ this.amount = amount; this.description = description; }
		
		@Override
		public int hashCode() {
			int result = 0;
			result = result + (59 * amount.hashCode());
			result = result + (59 * description.hashCode());
			return result;
		}
	}
	@Test
	public void dateAsCalendarNotUsed() {
		assertTestFails(DateAsCalendarNotUsed.class, HashCodeCase.ALL_FIELDS);
	}
	
	static class DateAsDateNotUsed {
		private final BigDecimal amount;
		private final String description;
		public DateAsDateNotUsed(BigDecimal amount, Date date, String description)
				{ this.amount = amount; this.description = description; }
		
		@Override
		public int hashCode() {
			int result = 0;
			result = result + (59 * amount.hashCode());
			result = result + (59 * description.hashCode());
			return result;
		}
	}
	@Test
	public void dateAsDateNotUsed() {
		assertTestFails(DateAsDateNotUsed.class, HashCodeCase.ALL_FIELDS);
	}
	
	static class DescriptionNotUsed {
		private final BigDecimal amount;
		private final Calendar date;
		public DescriptionNotUsed(BigDecimal amount, Calendar date, String description)
				{ this.amount = amount; this.date = date; }
		
		@Override
		public int hashCode() {
			int result = 0;
			result = result + (59 * amount.hashCode());
			result = result + (59 * date.hashCode());
			return result;
		}
	}
	@Test
	public void descriptionNotUsed() {
		assertTestFails(DescriptionNotUsed.class, HashCodeCase.ALL_FIELDS);
	}
	
	static class EqualsFinalButNotHashCode {
		private final BigDecimal amount;
		private final Calendar date;
		private final String description;
		public EqualsFinalButNotHashCode(BigDecimal amount, Calendar date, String description)
				{ this.amount = amount; this.date = date; this.description = description; }
		
		@Override
		public final boolean equals(Object obj) {
			if (!(obj instanceof EqualsFinalButNotHashCode)) {
				return false;
			}
			EqualsFinalButNotHashCode other = (EqualsFinalButNotHashCode)obj;
			return amount.equals(other.amount) && date.equals(other.date) && description.equals(other.description);
		}
		
		@Override
		public int hashCode() {
			int result = 0;
			result = result + (59 * amount.hashCode());
			result = result + (59 * date.hashCode());
			result = result + (59 * description.hashCode());
			return result;
		}
	}
	@Test
	public void equalsIsFinalButNotHashCode() {
		assertTestFails(EqualsFinalButNotHashCode.class, HashCodeCase.FINAL_BALANCE);
	}

	static class HashCodeFinalButNotEquals {
		private final BigDecimal amount;
		private final Calendar date;
		private final String description;
		public HashCodeFinalButNotEquals(BigDecimal amount, Calendar date, String description)
				{ this.amount = amount; this.date = date; this.description = description; }
		
		@Override
		public final boolean equals(Object obj) {
			if (!(obj instanceof HashCodeFinalButNotEquals)) {
				return false;
			}
			HashCodeFinalButNotEquals other = (HashCodeFinalButNotEquals)obj;
			return amount.equals(other.amount) && date.equals(other.date) && description.equals(other.description);
		}
		
		@Override
		public int hashCode() {
			int result = 0;
			result = result + (59 * amount.hashCode());
			result = result + (59 * date.hashCode());
			result = result + (59 * description.hashCode());
			return result;
		}
	}
	@Test
	public void hashCodeIsFinalButNotEquals() {
		assertTestFails(EqualsFinalButNotHashCode.class, HashCodeCase.FINAL_BALANCE);
	}
}
