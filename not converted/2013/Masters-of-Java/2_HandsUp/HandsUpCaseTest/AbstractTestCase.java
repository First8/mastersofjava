public abstract class AbstractTestCase implements HandsUpTestCase {
	private final String name;

	protected AbstractTestCase(String name) {
		this.name = name;
	}

	@Override
	public final String getName() {
		return this.name;
	}
}
