
public abstract class MojTest {
	private final String name;
	private final String description;
	private String errorMessage;
	
	public MojTest(String name, String description) {
		this.name = name;
		this.description = description;
		this.errorMessage = null;
	}
	
	public final boolean performTestOn(Class<?> type) {
		runTests(type);
		return errorMessage == null;
	}

	public abstract void runTests(Class<?> type);
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	protected void provideErrorMessage(String errorMessage) {
		if (this.errorMessage == null) {
			this.errorMessage = errorMessage;
		}
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
}
