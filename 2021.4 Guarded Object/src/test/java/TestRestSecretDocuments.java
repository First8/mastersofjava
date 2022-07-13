import org.junit.Test;

public class TestRestSecretDocuments {

	@Test(expected = SecurityException.class)
	public void testRestAPIMayNotAccessSecretDocuments() {
		createRestAPI().getDocumentsIncludingSecrets();	
	}

	public static RestAPI createRestAPI() {
		DocumentDao dao = new DocumentDao();
		DocumentService service = new DocumentService(dao);
		return new RestAPI(service);
	}
}
