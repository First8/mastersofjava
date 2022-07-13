
/**
 * A possibly sensitive document. The classification is according to our ISO27001 ISMS.
 */
public class Document {
	
	enum Classification {
		PUBLIC, NOT_PUBLIC
	}
	
	private final String content;
	private final Classification classification;
	
	public String getContent() {
		return content;
	}

	public Classification getClassification() {
		return classification;
	}

	public Document(String content, Classification classification) {
		this.content = content;
		this.classification = classification;
	}

}
