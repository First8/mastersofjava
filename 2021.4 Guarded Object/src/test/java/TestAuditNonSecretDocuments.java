import org.junit.Test;

public class TestAuditNonSecretDocuments {
	@Test
	public void testInternalAuditMayAccessNonSecretDocuments() {
		createInternalAudit().getDocuments();	
	}

	
	public static InternalAudit createInternalAudit() {
		DocumentDao dao = new DocumentDao();
		DocumentService service = new DocumentService(dao);
		return new InternalAudit(service);
	}
}
