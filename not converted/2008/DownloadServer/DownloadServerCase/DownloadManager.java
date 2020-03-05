import java.io.InputStream;

/**
 * Manages access to the downloads stored in a database.
 * Before calling the getDownloadById method a transaction must be started.
 * @author A.R.Chitect 
 */
public interface DownloadManager {

	/**
	 * starts a database transaction.
	 * @throws ManagerException if the transaction could not be started.
	 */
	public void beginTransaction() throws ManagerException;
	
	/**
	 * opens a new inputstream for reading the specified download.
	 * @param id the unique id of the download.
	 * @return an input stream to read the data from.
	 * @throws ManagerException if something goes wrong with the database.
	 */
	public InputStream getDownloadById(long id) throws ManagerException;
	
	/**
	 * ends the transaction with the database.
	 * @throws ManagerException if the connection with the database was lost.
	 */
	public void endTransaction() throws ManagerException;
	
}
