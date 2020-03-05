import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import solution_attempts.CorrectTransactionWithCalendar;

public class ToStringCaseTest extends TestBase {
	@Before
	public void setUp() {
		test = new ToStringCase();
	}

	@Test
	public void fullToString() {
		assertTestSucceeds(CorrectTransactionWithCalendar.class);
	}
	
	@Test
	public void cannotInstantiate() {
		assertTestFails(Transaction.class, ObjectAccessor.CANNOT_INSTANTIATE);
	}
	
	static class AmountMissing {
		@SuppressWarnings("unused")
		private final BigDecimal amount;
		private final Date date;
		private final String description;

		public AmountMissing(BigDecimal amount, Date date, String description) {
			this.amount = amount;
			this.date = date;
			this.description = description;
		}

		@Override
		public String toString() {
			return date.toString() + description;
		}
	}
	@Test
	public void amountMissing() {
		assertTestFails(AmountMissing.class, ToStringCase.PRESENCE);
	}
	
	static class DateAsCalendarMissing {
		private final BigDecimal amount;
		@SuppressWarnings("unused")
		private final Calendar date;
		private final String description;
		
		public DateAsCalendarMissing(BigDecimal amount, Calendar date, String description) {
			this.amount = amount;
			this.date = date;
			this.description = description;
		}

		@Override
		public String toString() {
			return amount.toString() + description;
		}
	}
	@Test
	public void dateAsCalendarMissing() {
		assertTestFails(DateAsCalendarMissing.class, ToStringCase.PRESENCE);
	}
	
	static class DateAsDateMissing {
		private final BigDecimal amount;
		@SuppressWarnings("unused")
		private final Date date;
		private final String description;

		public DateAsDateMissing(BigDecimal amount, Date date, String description) {
			this.amount = amount;
			this.date = date;
			this.description = description;
		}

		@Override
		public String toString() {
			return amount.toString() + description;
		}
	}
	@Test
	public void dateAsDateMissing() {
		assertTestFails(DateAsDateMissing.class, ToStringCase.PRESENCE);
	}
	
	static class DescriptionMissing {
		private final BigDecimal amount;
		private final Date date;
		@SuppressWarnings("unused")
		private final String description;

		public DescriptionMissing(BigDecimal amount, Date date, String description) {
			this.amount = amount;
			this.date = date;
			this.description = description;
		}

		@Override
		public String toString() {
			return amount.toString() + date.toString();
		}
	}
	@Test
	public void descriptionMissing() {
		assertTestFails(DescriptionMissing.class, ToStringCase.PRESENCE);
	}
	
	static class CalendarMakesItTooLong {
		private final BigDecimal amount;
		private final Calendar date;
		private final String description;

		public CalendarMakesItTooLong(BigDecimal amount, Calendar date, String description) {
			this.amount = amount;
			this.date = date;
			this.description = description;
		}

		@Override
		public String toString() {
			return amount.toString() + date.toString() + description;
		}
	}
	@Test
	public void calendarMakesItTooLong() {
		assertTestFails(CalendarMakesItTooLong.class, ToStringCase.LENGTH);
	}
}
