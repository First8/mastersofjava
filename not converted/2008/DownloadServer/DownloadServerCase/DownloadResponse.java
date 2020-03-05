import java.io.OutputStream;

/**
 * The response allows the download to be sent to the client. 
 */
public interface DownloadResponse {

	/**
	 * set to true if the download was successfully transmitted using the output stream.
	 * @param success true if successful.
	 */
	void setSuccess(boolean success);
	
	/**
	 * @return the output stream to send the data to the client.
	 */
	OutputStream getOutputStream();
	
}
