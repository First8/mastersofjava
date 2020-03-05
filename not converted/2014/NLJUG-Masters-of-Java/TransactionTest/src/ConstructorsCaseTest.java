import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import solution_attempts.CorrectTransactionWithCalendar;

@SuppressWarnings("unused")
public class ConstructorsCaseTest extends TestBase {
	@Before
	public void setUp() {
		test = new ConstructorsCase();
	}
	
	@Test
	public void noDefaultConstructorIsPresent() {
		assertTestSucceeds(CorrectTransactionWithCalendar.class);
	}
	
	static class DefaultConstructor {
		// Class must be static and non-local, or else the compiler will add constructor parameters...
		public DefaultConstructor() {}
	}
	@Test
	public void aDefaultConstructorIsPresent() {
		assertTestFails(DefaultConstructor.class, ConstructorsCase.DEFAULT_CONSTRUCTOR);
	}
	
	@Test
	public void thereIsOnlyOneConstructor() {
		assertTestSucceeds(CorrectTransactionWithCalendar.class);
	}
	
	static class MoreThanOneConstructor {
		public MoreThanOneConstructor(BigDecimal amount) {}
		public MoreThanOneConstructor(BigDecimal amount, Calendar date, String description) {}
	}
	@Test
	public void thereIsMoreThanOneConstructor() {
		assertTestFails(MoreThanOneConstructor.class, ConstructorsCase.ONE_CONSTRUCTOR);
	}
	
	@Test
	public void allExpectedFieldsArePresent() {
		assertTestSucceeds(CorrectTransactionWithCalendar.class);
	}
	
	static class MissingAmount {
		private BigDecimal amount;
		private Calendar date;
		private String description;
		public MissingAmount(Calendar date, String description) {}
	}
	@Test
	public void missingAmount() {
		assertTestFails(MissingAmount.class, ConstructorsCase.A_PARAMETER_FOR_EVERY_FIELD);
	}
	
	static class MissingDate {
		private BigDecimal amount;
		private Calendar date;
		private String description;
		public MissingDate(BigDecimal amount, String description) {}
	}
	@Test
	public void missingDate() {
		assertTestFails(MissingDate.class, ConstructorsCase.A_PARAMETER_FOR_EVERY_FIELD);
	}
	
	static class MissingDescription {
		private BigDecimal amount;
		private Calendar date;
		private String description;
		public MissingDescription(BigDecimal amount, Calendar date) {}
	}
	@Test
	public void missingDescription() {
		assertTestFails(MissingDescription.class, ConstructorsCase.A_PARAMETER_FOR_EVERY_FIELD);
	}
	
	@Test
	public void typesCorrespondToFields() {
		assertTestSucceeds(CorrectTransactionWithCalendar.class);
	}
	
	static class AmountIsDifferent {
		private BigDecimal amount;
		private Calendar date;
		private String description;
		public AmountIsDifferent(double amount, Calendar date, String description) {}
	}
	@Test
	public void amountIsDifferent() {
		assertTestFails(AmountIsDifferent.class, ConstructorsCase.A_PARAMETER_FOR_EVERY_FIELD);
	}
	
	static class AmountIsDifferentConsistently {
		private double amount;
		private Calendar date;
		private String description;
		public AmountIsDifferentConsistently(double amount, Calendar date, String description) {}
	}
	@Test
	public void amountIsDifferentConsistentyl() {
		assertTestSucceeds(AmountIsDifferentConsistently.class);
	}
	
	static class DateIsDifferent {
		private BigDecimal amount;
		private Calendar date;
		private String description;
		public DateIsDifferent(BigDecimal amount, Date date, String description) {}
	}
	@Test
	public void dateIsDifferent() {
		assertTestFails(DateIsDifferent.class, ConstructorsCase.A_PARAMETER_FOR_EVERY_FIELD);
	}
	
	static class DateIsDifferentConsistently {
		private BigDecimal amount;
		private GregorianCalendar date;
		private String description;
		public DateIsDifferentConsistently(BigDecimal amount, GregorianCalendar date, String description) {}
	}
	@Test
	public void dateIsDifferentConsistently() {
		assertTestSucceeds(DateIsDifferentConsistently.class);
	}
	
	static class DescriptionIsDifferent {
		private BigDecimal amount;
		private Calendar date;
		private String description;
		public DescriptionIsDifferent(BigDecimal amount, Calendar date, char[] description) {}
	}
	@Test
	public void descriptionIsDifferent() {
		assertTestFails(DescriptionIsDifferent.class, ConstructorsCase.A_PARAMETER_FOR_EVERY_FIELD);
	}
	
	@Test
	public void throwsOnAllNullFields() {
		assertTestSucceeds(CorrectTransactionWithCalendar.class);
	}
	
	static class AmountDoesntThrow {
		private BigDecimal amount;
		private Calendar date;
		private String description;
		public AmountDoesntThrow(BigDecimal amount, Calendar date, String description) {
			if (date == null) throw new NullPointerException("date");
			if (description == null) throw new NullPointerException("description");
		}
	}
	@Test
	public void doesntThrowOnNullAmount() {
		assertTestFails(AmountDoesntThrow.class, ConstructorsCase.THROW_NPE);
	}
	
	static class DateAsCalendarDoesntThrow {
		private BigDecimal amount;
		private Calendar date;
		private String description;
		public DateAsCalendarDoesntThrow(BigDecimal amount, Calendar date, String description) {
			if (amount == null) throw new NullPointerException("amount");
			if (description == null) throw new NullPointerException("description");
		}
	}
	@Test
	public void doesntThrowOnNullDateAsCalendar() {
		assertTestFails(DateAsCalendarDoesntThrow.class, ConstructorsCase.THROW_NPE);
	}
	
	static class DateAsDateDoesntThrow {
		private BigDecimal amount;
		private Date date;
		private String description;
		public DateAsDateDoesntThrow(BigDecimal amount, Date date, String description) {
			if (amount == null) throw new NullPointerException("amount");
			if (description == null) throw new NullPointerException("description");
		}
	}
	@Test
	public void doesntThrowOnNullDateAsDate() {
		assertTestFails(DateAsDateDoesntThrow.class, ConstructorsCase.THROW_NPE);
	}
	
	static class DescriptionDoesntThrow {
		private BigDecimal amount;
		private Date date;
		private String description;
		public DescriptionDoesntThrow(BigDecimal amount, Date date, String description) {
			if (amount == null) throw new NullPointerException("amount");
			if (date == null) throw new NullPointerException("date");
		}
	}
	@Test
	public void doesntThrowOnNullDescription() {
		assertTestFails(DescriptionDoesntThrow.class, ConstructorsCase.THROW_NPE);
	}
	
	static class ThrowsTheWrongException {
		private BigDecimal amount;
		private Date date;
		private String description;
		public ThrowsTheWrongException(BigDecimal amount, Date date, String description) {
			if (amount == null) throw new IllegalArgumentException("amount");
			if (date == null) throw new IllegalArgumentException("date");
			if (description == null) throw new IllegalArgumentException("description");
		}
	}
	@Test
	public void throwsTheWrongException() {
		assertTestFails(ThrowsTheWrongException.class, ConstructorsCase.THROW_NPE);
	}
	
	static class DoesntMentionOffendingField {
		private BigDecimal amount;
		private Date date;
		private String description;
		public DoesntMentionOffendingField(BigDecimal amount, Date date, String description) {
			if (amount == null) throw new NullPointerException();
			if (date == null) throw new NullPointerException();
			if (description == null) throw new NullPointerException();
		}
	}
	@Test
	public void doesntMentionOffendingField() {
		assertTestFails(DoesntMentionOffendingField.class, ConstructorsCase.THROW_NPE);
	}
}
