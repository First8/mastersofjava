import org.junit.Test;

public class TestRestNonSecretDocuments {
	@Test
	public void testRestAPIMayAccessNonSecretDocuments() {
		createRestAPI().getDocuments();	
	}

	public static RestAPI createRestAPI() {
		DocumentDao dao = new DocumentDao();
		DocumentService service = new DocumentService(dao);
		return new RestAPI(service);
	}
	
}
