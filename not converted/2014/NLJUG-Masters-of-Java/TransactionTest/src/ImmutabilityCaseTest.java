import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import solution_attempts.CorrectTransactionWithCalendar;
import solution_attempts.CorrectTransactionWithDate;
import solution_attempts.MutableTransaction;

public class ImmutabilityCaseTest extends TestBase {
	@Before
	public void setUp() {
		test = new ImmutabilityCase();
	}
	
	@Test
	public void indeedImmutableWithCalendar() {
		assertTestSucceeds(CorrectTransactionWithCalendar.class);
	}

	@Test
	public void indeedImmutableWithDate() {
		assertTestSucceeds(CorrectTransactionWithDate.class);
	}
	
	@Test
	public void cannotInstantiate() {
		assertTestFails(Transaction.class, ObjectAccessor.CANNOT_INSTANTIATE);
	}

	@Test
	public void mutableFields() {
		assertTestFails(MutableTransaction.class, ImmutabilityCase.IMMUTABLE);
	}

	@SuppressWarnings("unused")
	static class ConstructorDoesntCopyDate {
		private final BigDecimal amount;
		private final Date date;
		private final String description;

		public ConstructorDoesntCopyDate(BigDecimal amount, Date date, String description) {
			this.amount = amount;
			this.date = date;
			this.description = description;
		}
		
		public Date getDate() {
			return (Date)date.clone();
		}
	}
	@Test
	public void constructorDoesntCloneDate() {
		assertTestFails(ConstructorDoesntCopyDate.class, ImmutabilityCase.CONSTRUCTOR);
	}
	
	@SuppressWarnings("unused")
	static class ConstructorDoesntCopyCalendar {
		private final BigDecimal amount;
		private final Calendar date;
		private final String description;

		public ConstructorDoesntCopyCalendar(BigDecimal amount, Calendar date, String description) {
			this.amount = amount;
			this.date = date;
			this.description = description;
		}
		
		public Calendar getDate() {
			return (Calendar)date.clone();
		}
	}
	@Test
	public void constructorDoesntCloneCalendar() {
		assertTestFails(ConstructorDoesntCopyCalendar.class, ImmutabilityCase.CONSTRUCTOR);
	}
	
	@SuppressWarnings("unused")
	static class GetterDoesntCopyDate {
		private final BigDecimal amount;
		private final Date date;
		private final String description;

		public GetterDoesntCopyDate(BigDecimal amount, Date date, String description) {
			this.amount = amount;
			this.date = (Date)date.clone();
			this.description = description;
		}
		
		public Date getDate() {
			return date;
		}
	}
	@Test
	public void getterDoesntCloneDate() {
		assertTestFails(GetterDoesntCopyDate.class, ImmutabilityCase.GETTER);
	}
	
	@SuppressWarnings("unused")
	static class GetterDoesntCopyCalendar {
		private final BigDecimal amount;
		private final Calendar date;
		private final String description;

		public GetterDoesntCopyCalendar(BigDecimal amount, Calendar date, String description) {
			this.amount = amount;
			this.date = (Calendar)date.clone();
			this.description = description;
		}
		
		public Calendar getDate() {
			return date;
		}
	}
	@Test
	public void getterDoesntCloneCalendar() {
		assertTestFails(GetterDoesntCopyCalendar.class, ImmutabilityCase.GETTER);
	}
}
