import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class ImmutabilityCase extends MojTest {
	public static final String IMMUTABLE = "Transaction should be an immutable class. Its values are not allowed to change after construction.";
	public static final String CONSTRUCTOR = "The class you use for the date field is mutable, so your constructor must take care that Transaction is still immutable.";
	public static final String GETTER = "The class you use for the date field is mutable, so your getter must take care that Transaction is still immutable.";
	
	public ImmutabilityCase() {
		super("Immutability", "Make your class immutable. Once constructed, it is not allowed to change for any reason.");
	}
	
	@Override
	public void runTests(Class<?> type) {
		if (attemptInstantiate(type) == null) {
			provideErrorMessage(ObjectAccessor.CANNOT_INSTANTIATE);
			return;
		}

		provideErrorMessage(fieldsAreFinal(type));
		provideErrorMessage(constructorClonesDate(type));
		provideErrorMessage(getterClonesDate(type));
	}

	private Object attemptInstantiate(Class<?> type) {
		try {
			return ObjectAccessor.instantiate(type, ObjectAccessor.createPrefabValues());
		}
		catch (Throwable e) {
			return null;
		}
	}
	
	private String fieldsAreFinal(Class<?> type) {
		boolean allFieldsAreFinal = fieldIsFinal(type, "amount") && fieldIsFinal(type, "date") && fieldIsFinal(type, "description");
		if ( ! allFieldsAreFinal) {
			return IMMUTABLE;
		}
		return null;
	}
	
	private boolean fieldIsFinal(Class<?> type, String fieldName) {
		try {
			Field field = type.getDeclaredField(fieldName);
			return Modifier.isFinal(field.getModifiers());
		}
		catch (NoSuchFieldException e) {
			return false;
		}
		catch (SecurityException e) {
			return false;
		}
	}

	private String constructorClonesDate(Class<?> type) {
		try {
			Class<?> typeOfDate = ObjectAccessor.getTypeOfDate(type);
			Map<Class<?>, Object> prefabValues = ObjectAccessor.createPrefabValues();

			Object injected = prefabValues.get(typeOfDate);
			Object expected = copy(injected);
			Object obj = ObjectAccessor.instantiate(type, prefabValues);
			modify(injected);
			if (ObjectAccessor.getDate(obj).equals(expected)) {
				return null;
			}
			return CONSTRUCTOR;
		}
		catch (Throwable e) {
			return e.toString();
		}
	}
	
	public String getterClonesDate(Class<?> type) {
		try {
			Object obj = ObjectAccessor.instantiate(type, ObjectAccessor.createPrefabValues());
			Object date = ObjectAccessor.getDate(obj);
			Object expected = copy(date);
			modify(date);
			Object actual = ObjectAccessor.getDate(obj);
			if (expected.equals(actual)) {
				return null;
			}
			return GETTER;
		}
		catch (Throwable e) {
			return e.toString();
		}
	}

	private static void modify(Object date) {
		if (date.getClass().equals(Date.class)) {
			Date d = (Date)date;
			d.setTime(d.getTime() + 100000);
		}
		else {
			((Calendar)date).add(Calendar.DAY_OF_YEAR, 13);
		}
	}
	
	private static Object copy(Object original) {
		if (original.getClass().equals(Date.class)) {
			return ((Date)original).clone();
		}
		else {
			return ((Calendar)original).clone();
		}
	}
}
