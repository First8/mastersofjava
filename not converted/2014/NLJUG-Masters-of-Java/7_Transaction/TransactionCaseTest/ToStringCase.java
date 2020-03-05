import java.math.BigDecimal;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

public class ToStringCase extends MojTest {
	public static final String LENGTH = "This toString is so long, it's not human-readable.";
	public static final String PRESENCE = "toString should show the complete state of the object.";
	
	public ToStringCase() {
		super("toString", "Implement a human-readable toString to represent the class and make debugging easy.");
	}

	@Override
	public void runTests(Class<?> type) {
		if (attemptInstantiate(type) == null) {
			provideErrorMessage(ObjectAccessor.CANNOT_INSTANTIATE);
			return;
		}

		provideErrorMessage(toString(type));
	}

	private Object attemptInstantiate(Class<?> type) {
		try {
			return ObjectAccessor.instantiate(type, ObjectAccessor.createPrefabValues());
		}
		catch (Throwable e) {
			return null;
		}
	}

	public String toString(Class<?> type) {
		try {
			Map<Class<?>, Object> prefabValues = ObjectAccessor.createPrefabValues();
			Object obj = ObjectAccessor.instantiate(type, prefabValues);
			String toString = obj.toString();
			if (toString.length() > 100) {
				return LENGTH;
			}
			if ( ! toString.contains(prefabValues.get(BigDecimal.class).toString())) {
				return PRESENCE;
			}
			if ( ! containsDateFragments(toString, prefabValues.get(ObjectAccessor.getTypeOfDate(type)))) {
				return PRESENCE;
			}
			if ( ! toString.contains(prefabValues.get(String.class).toString())) {
				return PRESENCE;
			}
			return null;
		}
		catch (Throwable e) {
			return e.toString();
		}
	}
	
	private boolean containsDateFragments(String toString, Object date) {
		Calendar c = null;
		if (date.getClass().equals(Date.class)) {
			c = new GregorianCalendar();
			c.setTime((Date)date);
		}
		else {
			c = (Calendar)date;
		}
		
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH) + 1; // Note that months are zero-based!
		int year = c.get(Calendar.YEAR);
		
		return toString.contains("" + day) && toString.contains("" + month) && toString.contains("" + year);
	}
}
