import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ConstructorsCase extends MojTest {
	public static final String DEFAULT_CONSTRUCTOR = "I cannot initialize a Transaction properly with a default constructor.";
	public static final String ONE_CONSTRUCTOR = "Why have constructors that don't initialize every field, or that have more parameters than there are fields?";
	public static final String A_PARAMETER_FOR_EVERY_FIELD = "There should be exactly 1 constructor parameter for each field.";
	public static final String DO_NOT_USE_ASSERTIONS = "You shouldn't use 'assert' to check for null values. Assertions can be turned off in production.";
	public static final String THROW_NPE = "None of the fields should ever be null, and the constructor should check that. If a field was null, it should say which one.";

	public ConstructorsCase() {
		super("Constructor", "Implement an appropriate constructor.");
	}
	
	@Override
	public void runTests(Class<?> type) {
		provideErrorMessage(noDefaultConstructor(type));
		provideErrorMessage(onlyOneConstructor(type));
		provideErrorMessage(doNotUseAssertionsToCheckForNullValues(type));
		provideErrorMessage(thereAreParametersForEachField(type));
		provideErrorMessage(constructorThrowsNpeIfFieldsAreNull(type));
	}

	public String noDefaultConstructor(java.lang.Class<?> type) {
		for (Constructor<?> c : type.getConstructors()) {
			if (c.getParameterTypes().length == 0) {
				return DEFAULT_CONSTRUCTOR;
			}
		}
		return null;
	}
	
	public String onlyOneConstructor(java.lang.Class<?> type) {
		if (type.getConstructors().length != 1) {
			return ONE_CONSTRUCTOR;
		}
		return null;
	}
	
	public String doNotUseAssertionsToCheckForNullValues(Class<?> type) {
		Field[] fields = type.getDeclaredFields();
		for(Field field : fields) {
			if(field.getName().contains("assert")) {
				return DO_NOT_USE_ASSERTIONS;
			}
		}
		
		return null;
	}
	
	public String thereAreParametersForEachField(Class<?> type) {
		List<Class<?>> params = new ArrayList<Class<?>>(Arrays.asList(getConstructorParams(type)));
		Field[] fields = type.getDeclaredFields();
		if (fields.length != params.size()) {
			return A_PARAMETER_FOR_EVERY_FIELD;
		}
		for (Field f : fields) {
			Class<?> fieldType = f.getType();
			boolean found = params.remove(fieldType);
			if ( ! found) {
				return A_PARAMETER_FOR_EVERY_FIELD;
			}
		}
		return null;
	}
	
	public String constructorThrowsNpeIfFieldsAreNull(Class<?> type) {
		if (dontCheckIfTypesDontCheckOut(type)) {
			return null;
		}
		boolean result = checkNullParameterThrows(type, BigDecimal.class, "amount") &&
				checkNullParameterThrows(type, ObjectAccessor.getTypeOfDate(type), "date") &&
				checkNullParameterThrows(type, String.class, "description");
		if (result) {
			return null;
		}
		return THROW_NPE;
	}
	
	private boolean dontCheckIfTypesDontCheckOut(Class<?> type) {
		List<Class<?>> params = Arrays.asList(getConstructorParams(type));
		if ( ! params.contains(BigDecimal.class)) {
			return true;
		}
		if ( ! (params.contains(Date.class) || params.contains(Calendar.class))) {
			return true;
		}
		if ( ! params.contains(String.class)) {
			return true;
		}
		return false;
	}

	private boolean checkNullParameterThrows(Class<?> type, Class<?> parameterType, String parameterName) {
		Map<Class<?>, Object> prefabValues = ObjectAccessor.createPrefabValues();
		prefabValues.remove(parameterType);
		
		try {
			ObjectAccessor.instantiate(type, prefabValues);
			return false;
		}
		catch (NullPointerException e) {
			return parameterName.equals(e.getMessage());
		}
		catch (Throwable e) {
			return false;
		}
	}
	
	private static Class<?>[] getConstructorParams(Class<?> type) {
		Constructor<?> constructor = type.getConstructors()[0];
		return constructor.getParameterTypes();
	}
}
