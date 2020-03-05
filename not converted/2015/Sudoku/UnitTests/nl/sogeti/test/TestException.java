package nl.sogeti.test;

public class TestException extends RuntimeException {

	private static final long serialVersionUID = 6866505094808344494L;

	public TestException(final String msg) {
		super(msg);
	}
}