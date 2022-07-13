import java.security.GuardedObject;
import java.util.Collections;
import java.util.List;

/**
 * Data access object for accessing documents in the database.
 */
public class DocumentDao {

	public List<GuardedObject> getTopSecretDocuments() {
		GuardedObject doc = new GuardedObject(new Document("top secret content!", Document.Classification.NOT_PUBLIC),
				new Iso27001ComplianceGuard());
		return Collections.singletonList(doc);
	}

	public List<GuardedObject> getNormalDocuments() {
		GuardedObject doc = new GuardedObject(new Document("not so secret document", Document.Classification.PUBLIC),
				new Iso27001ComplianceGuard());
		return Collections.singletonList(doc);
	}

}
