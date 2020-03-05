import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import solution_attempts.CorrectTransactionWithCalendar;
import solution_attempts.CorrectTransactionWithDate;

@SuppressWarnings("unused")
public class FieldsCaseTest extends TestBase {
	@Before
	public void setUp() {
		test = new FieldsCase();
	}
	
	@Test
	public void allExpectedFieldsArePresent() {
		assertTestSucceeds(CorrectTransactionWithCalendar.class);
	}
	
	@Test
	public void missingAmount() {
		class MissingAmount {
			private Calendar date;
			private String description;
		}
		assertTestFails(MissingAmount.class, FieldsCase.PRESENCE);
	}
	
	@Test
	public void missingDate() {
		class MissingDate {
			private BigDecimal amount;
			private String description;
		}
		assertTestFails(MissingDate.class, FieldsCase.PRESENCE);
	}
	
	@Test
	public void missingDescription() {
		class MissingDescription {
			private BigDecimal amount;
			private Calendar date;
		}
		assertTestFails(MissingDescription.class, FieldsCase.PRESENCE);
	}
	
	@Test
	public void descriptionIsAString() {
		assertTestSucceeds(CorrectTransactionWithCalendar.class);
	}
	
	@Test
	public void descriptionIsSomethingElse() {
		class DescriptionIsAnInt {
			private BigDecimal amount;
			private Calendar date;
			private int description;
		}
		assertTestFails(DescriptionIsAnInt.class, FieldsCase.DESCRIPTION);
	}
	
	@Test
	public void amountIsNotADoubleOrFloat() {
		assertTestSucceeds(CorrectTransactionWithCalendar.class);
	}

	@Test
	public void amountIsADouble() {
		class AmountIsDouble {
			private double amount;
			private Calendar date;
			private String description;
		}
		assertTestFails(AmountIsDouble.class, FieldsCase.AMOUNT_DOUBLE);
	}

	@Test
	public void amountIsAFloat() {
		class AmountIsFloat {
			private float amount;
			private Calendar date;
			private String description;
		}
		assertTestFails(AmountIsFloat.class, FieldsCase.AMOUNT_FLOAT);
	}
	
	@Test
	public void amountIsNotAnIntOrLong() {
		assertTestSucceeds(CorrectTransactionWithCalendar.class);
	}
	
	@Test
	public void amountIsAnIntOrLong() {
		class AmountIsInt {
			private int amount;
			private Calendar date;
			private String description;
		}
		assertTestFails(AmountIsInt.class, FieldsCase.AMOUNT_INT_LONG);
	}
	
	@Test
	public void amountIsABigDecimal() {
		assertTestSucceeds(CorrectTransactionWithCalendar.class);
	}
	
	@Test
	public void amountIsSomethingElse() {
		class AmountIsString {
			private String amount;
			private Calendar date;
			private String description;
		}
		assertTestFails(AmountIsString.class, FieldsCase.AMOUNT);
	}
	
	@Test
	public void dateIsNotAGregorianCalendar() {
		assertTestSucceeds(CorrectTransactionWithCalendar.class);
	}
	
	@Test
	public void dateIsAGregorianCalendar() {
		class DateIsAGregorianCalendar {
			private BigDecimal amount;
			private GregorianCalendar date;
			private String description;
		}
		assertTestFails(DateIsAGregorianCalendar.class, FieldsCase.DATE_GREGORIANCALENDAR);
		
	}
	
	@Test
	public void dateIsACalendar() {
		assertTestSucceeds(CorrectTransactionWithCalendar.class);
	}
	
	@Test
	public void dateIsADate() {
		assertTestSucceeds(CorrectTransactionWithDate.class);
	}
	
	@Test
	public void dateIsSomethingElse() {
		class DateIsAString {
			private BigDecimal amount;
			private String date;
			private String description;
		}
		assertTestFails(DateIsAString.class, FieldsCase.DATE);
	}
	
	@Test
	public void amountIsNotPrivate() {
		class AmountIsNotPrivate {
			public BigDecimal amount;
			private Date date;
			private String description;
		}
		assertTestFails(AmountIsNotPrivate.class, FieldsCase.FIELD_IS_NOT_PRIVATE);
	}
	
	@Test
	public void dateIsNotPrivate() {
		class DateIsNotPrivate {
			private BigDecimal amount;
			protected Date date;
			private String description;
		}
		assertTestFails(DateIsNotPrivate.class, FieldsCase.FIELD_IS_NOT_PRIVATE);
	}
	
	@Test
	public void descriptionIsNotPrivate() {
		class DescriptionIsNotPrivate {
			private BigDecimal amount;
			private Date date;
			/*default*/ String description;
		}
		assertTestFails(DescriptionIsNotPrivate.class, FieldsCase.FIELD_IS_NOT_PRIVATE);
	}
}
