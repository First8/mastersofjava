import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A service concerning all operations regarding documents.
 */
public class DocumentService  {
	private final DocumentDao documentDao;

	public DocumentService(DocumentDao documentDao) {
		this.documentDao = documentDao;
	}
	
	public List<Document> getDocuments(boolean includeSecretDocuments) {
		var result = new ArrayList<>(documentDao.getNormalDocuments());
		if( includeSecretDocuments) {
			result.addAll(documentDao.getTopSecretDocuments());
		}
		
		// remove the guardedObject wrapper so we don't pollute our whole app with that nonsense
		return result.stream().map( guardedObject -> (Document)guardedObject.getObject()).collect(Collectors.toList());
	}
	

}
