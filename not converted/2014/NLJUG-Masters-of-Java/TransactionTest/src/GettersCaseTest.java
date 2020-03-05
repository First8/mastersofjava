import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import solution_attempts.CorrectTransactionWithCalendar;

@SuppressWarnings("unused")
public class GettersCaseTest extends TestBase {
	@Before
	public void setUp() {
		test = new GettersCase();
	}
	
	@Test
	public void allExpectedFieldsArePresent() {
		assertTestSucceeds(CorrectTransactionWithCalendar.class);
	}
	
	@Test
	public void missingAmount() {
		class MissingAmount {
			private Calendar getDate() { return null; }
			private String getDescription() { return null; }
		}
		assertTestFails(MissingAmount.class, GettersCase.PRESENCE);
	}
	
	@Test
	public void missingDate() {
		class MissingDate {
			private BigDecimal getAmount() { return null; }
			private String getDescription() { return null; }
		}
		assertTestFails(MissingDate.class, GettersCase.PRESENCE);
	}
	
	@Test
	public void missingDescription() {
		class MissingDescription {
			private BigDecimal getAmount() { return null; }
			private Calendar getDate() { return null; }
		}
		assertTestFails(MissingDescription.class, GettersCase.PRESENCE);
	}
	
	@Test
	public void typesCorrespondToFields() {
		assertTestSucceeds(CorrectTransactionWithCalendar.class);
	}
	
	@Test
	public void amountIsDifferent() {
		class AmountIsDifferent {
			private BigDecimal amount;
			public Float getAmount() { return null; }

			private Date date;
			public Date getDate() { return null; }

			private String description;
			public String getDescription() { return null; }
		}
		assertTestFails(AmountIsDifferent.class, GettersCase.CORRESPONDENCE);
	}
	
	@Test
	public void amountIsDifferentConsistently() {
		class AmountIsDifferentConsistently {
			private Float amount;
			public Float getAmount() { return null; }

			private Date date;
			public Date getDate() { return null; }

			private String description;
			public String getDescription() { return null; }
		}
		assertTestSucceeds(AmountIsDifferentConsistently.class);
	}
	
	@Test
	public void dateIsDifferent() {
		class DateIsDifferent {
			private BigDecimal amount;
			public BigDecimal getAmount() { return null; }

			private Calendar date;
			public Date getDate() { return null; }

			private String description;
			public String getDescription() { return null; }
		}
		assertTestFails(DateIsDifferent.class, GettersCase.CORRESPONDENCE);
	}
	
	@Test
	public void dateIsDifferentConsistentyl() {
		class DateIsDifferentConsistently {
			private BigDecimal amount;
			public BigDecimal getAmount() { return null; }

			private GregorianCalendar date;
			public GregorianCalendar getDate() { return null; }

			private String description;
			public String getDescription() { return null; }
		}
		assertTestSucceeds(DateIsDifferentConsistently.class);
	}
	
	@Test
	public void descriptionIsDifferent() {
		class DescriptionIsDifferent {
			private BigDecimal amount;
			public BigDecimal getAmount() { return null; }

			private Calendar date;
			public Calendar getDate() { return null; }

			private String description;
			public int getDescription() { return 0; }
		}
		assertTestFails(DescriptionIsDifferent.class, GettersCase.CORRESPONDENCE);
	}
	
	@Test
	public void fieldsMakeTheRoundTrip() {
		assertTestSucceeds(CorrectTransactionWithCalendar.class);
	}
	
	static class AmountMissing {
		private final BigDecimal amount;
		private final Calendar date;
		private final String description;
		public AmountMissing(BigDecimal amount, Calendar date, String description)
				{ this.amount = null; this.date = date; this.description = description; }
		public BigDecimal getAmount() { return new BigDecimal("-10"); }
		public Calendar getDate() { return date; }
		public String getDescription() { return description; }
	}
	@Test
	public void amountDoesntMakeTheRoundTrip() {
		assertTestFails(AmountMissing.class, GettersCase.ROUND_TRIP);
	}
	
	static class DateAsCalendarMissing {
		private final BigDecimal amount;
		private final Calendar date;
		private final String description;
		public DateAsCalendarMissing(BigDecimal amount, Calendar date, String description)
				{ this.amount = amount; this.date = null; this.description = description; }
		public BigDecimal getAmount() { return amount; }
		public Calendar getDate() { return new GregorianCalendar(2047, 0, 1); }
		public String getDescription() { return description; }
	}
	@Test
	public void dateAsCalendarDoesntMakeTheRoundTrip() {
		assertTestFails(DateAsCalendarMissing.class, GettersCase.ROUND_TRIP);
	}
	
	static class DateAsDateMissing {
		private final BigDecimal amount;
		private final Date date;
		private final String description;
		public DateAsDateMissing(BigDecimal amount, Date date, String description)
				{ this.amount = amount; this.date = null; this.description = description; }
		public BigDecimal getAmount() { return amount; }
		public Date getDate() { return new Date(0); }
		public String getDescription() { return description; }
	}
	@Test
	public void dateAsDateDoesntMakeTheRoundTrip() {
		assertTestFails(DateAsDateMissing.class, GettersCase.ROUND_TRIP);
	}
	
	static class DescriptionMissing {
		private final BigDecimal amount;
		private final Calendar date;
		private final String description;
		public DescriptionMissing(BigDecimal amount, Calendar date, String description)
				{ this.amount = amount; this.date = date; this.description = null; }
		public BigDecimal getAmount() { return amount; }
		public Calendar getDate() { return date; }
		public String getDescription() { return "no description"; }
	}
	@Test
	public void descriptionDoesntMakeTheRoundTrip() {
		assertTestFails(DescriptionMissing.class, GettersCase.ROUND_TRIP);
	}
}
