/**
 * Our REST API that allows our customers to access documents. 
 */
public class RestAPI  {
	private final DocumentService service;

	public RestAPI(DocumentService service) {
		this.service = service;
	}

	public void getDocuments() {
		System.out.println("RestAPI.getDocuments: ");
		service.getDocuments(false).forEach( d -> System.out.println(" * " + d.getContent()));
	}

	/**
	 *  According to our ISO implementation, this is not allowed. But rather than
	 *  trusting our developers, we need to somehow enforce this programmatically.
	 */
	public void getDocumentsIncludingSecrets() {
		System.out.println("RestAPI.getDocumentsIncludingSecrets: ");
		service.getDocuments(true).forEach( d -> System.out.println(" * " + d.getContent()));
	}

}
