/**
 * An internal audit tool that should be able to access all documents.
 */
public class InternalAudit  {
	private final DocumentService service;

	public InternalAudit(DocumentService service) {
		this.service = service;
	}

	public void getDocuments() {
		System.out.println("InternalAudit.getDocuments: ");
		service.getDocuments(false).forEach( d -> System.out.println(" * " + d.getContent()));
	}

	public void getDocumentsIncludingSecrets() {
		System.out.println("InternalAudit.getDocumentsIncludingSecrets: ");
		service.getDocuments(true).forEach( d -> System.out.println(" * " + d.getContent()));
	}

}
