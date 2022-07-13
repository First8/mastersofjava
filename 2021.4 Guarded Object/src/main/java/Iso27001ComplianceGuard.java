import java.security.Guard;

/**
 * This class enforces ISO 27001 compliance by checking if documents that are labeled NOT_PUBLIC are not externally distributed.
 * 
 * It only lacks implementation.
 */
public class Iso27001ComplianceGuard implements Guard {

	@Override
	public void checkGuard(Object object) throws SecurityException {
	}

}
