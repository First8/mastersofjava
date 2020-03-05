import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FieldsCase extends MojTest {
	public static final String PRESENCE = "Your class must have fields for the amount, the date, and a description.";
	public static final String AMOUNT_DOUBLE = "2.00 - 1.10 == 0.8999999999999999 -- imprecision can cost money!";
	public static final String AMOUNT_FLOAT = "33.33f / 10 == 3.3330002f -- imprecision can cost money!";
	public static final String AMOUNT_INT_LONG = "Ints and Longs are not ideal types for monetary values, because it's not clear where to put the decimal point";
	public static final String AMOUNT = "I don't understand how the type of 'amount' represents a monetary value";
	public static final String DATE_GREGORIANCALENDAR = "It's better to use a general interface than a concrete implementation";
	public static final String DATE = "I don't understand how the type of 'date' represents a date";
	public static final String DESCRIPTION = "I don't understand how the type of 'description' holds a description.";
	public static final String FIELD_IS_NOT_PRIVATE = "All your fields should be private.";

	public FieldsCase() {
		super("Fields", "Implement fields for the amount, the date, and a description.");
	}

	@Override
	public void runTests(Class<?> type) {
		provideErrorMessage(presenceOfFields(type));

		if (fieldHasType(type, "amount", double.class, Double.class)) {
			provideErrorMessage(AMOUNT_DOUBLE);
		}
		if (fieldHasType(type, "amount", float.class, Float.class)) {
			provideErrorMessage(AMOUNT_FLOAT);
		}
		if (fieldHasType(type, "amount", int.class, Integer.class, long.class, Long.class)) {
			provideErrorMessage(AMOUNT_INT_LONG);
		}
		if (fieldDoesntHaveType(type, "amount", BigDecimal.class)) {
			provideErrorMessage(AMOUNT);
		}
		
		if (fieldHasType(type, "date", GregorianCalendar.class)) {
			provideErrorMessage(DATE_GREGORIANCALENDAR);
		}
		if (fieldDoesntHaveType(type, "date", Calendar.class, Date.class)) {
			provideErrorMessage(DATE);
		}

		if (fieldDoesntHaveType(type, "description", String.class)) {
			provideErrorMessage(DESCRIPTION);
		}
		
		provideErrorMessage(fieldsArePrivate(type));
	}
	
	private String presenceOfFields(Class<?> type) {
		boolean result = hasFieldNamed(type, "amount") &&
				hasFieldNamed(type, "date") &&
				hasFieldNamed(type, "description");
		if (result) {
			return null;
		}
		return PRESENCE;
	}

	public boolean fieldHasType(Class<?> type, String fieldName, Class<?>... fieldTypes) {
		for (Class<?> fieldType : fieldTypes) {
			if (fieldHasSpecificType(type, fieldName, fieldType)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean fieldDoesntHaveType(Class<?> type, String fieldName, Class<?>... fieldTypes) {
		for (Class<?> fieldType : fieldTypes) {
			if (fieldHasSpecificType(type, fieldName, fieldType)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean hasFieldNamed(Class<?> type, String name) {
		try {
			type.getDeclaredField(name);
			return true;
		}
		catch (SecurityException e) {
			return false;
		}
		catch (NoSuchFieldException e) {
			return false;
		}
	}

	private boolean fieldHasSpecificType(Class<?> type, String fieldName, Class<?> fieldType) {
		try {
			Field actualFieldType = type.getDeclaredField(fieldName);
			return actualFieldType.getType().equals(fieldType);
		}
		catch (SecurityException e) {
			return false;
		}
		catch (NoSuchFieldException e) {
			return false;
		}
	}
	
	private String fieldsArePrivate(Class<?> type) {
		if (fieldIsPrivate(type, "amount") && fieldIsPrivate(type, "date") && fieldIsPrivate(type, "description")) {
			return null;
		}
		return FIELD_IS_NOT_PRIVATE;
	}
	
	private boolean fieldIsPrivate(Class<?> type, String fieldName) {
		try {
			Field field = type.getDeclaredField(fieldName);
			return Modifier.isPrivate(field.getModifiers());
		}
		catch (NoSuchFieldException e) {
			return false;
		}
		catch (SecurityException e) {
			return false;
		}
	}
}
