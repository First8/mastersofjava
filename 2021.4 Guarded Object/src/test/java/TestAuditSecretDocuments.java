import org.junit.Test;

public class TestAuditSecretDocuments {

	@Test
	public void testInternalAuditMayAccessSecretDocuments() {
		createInternalAudit().getDocumentsIncludingSecrets();	
	}

	public static InternalAudit createInternalAudit() {
		DocumentDao dao = new DocumentDao();
		DocumentService service = new DocumentService(dao);
		return new InternalAudit(service);
	}
}
