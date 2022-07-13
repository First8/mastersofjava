import java.security.Guard;

/**
 * This class enforces ISO 27001 compliance by checking if documents that are labeled NOT_PUBLIC are not externally distributed.
 */
public class Iso27001ComplianceGuard implements Guard {

	@Override
	public void checkGuard(Object object) throws SecurityException {
		if (object instanceof Document) {
			if ( ((Document) object).getClassification()==Document.Classification.NOT_PUBLIC) {
				StackWalker.getInstance().forEach( sf -> {
					if (sf.getClassName().contains("RestAPI")) {
							throw new SecurityException("RestAPI should not access non-public documents");
					}
				});
			}
		}
	}

}
