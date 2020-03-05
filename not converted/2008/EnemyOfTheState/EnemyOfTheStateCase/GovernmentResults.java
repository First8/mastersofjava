/**
 * Tells the Fugitive what the Government has been doing. 
 * After the invocation of one of these methods the Fugitive may perform an action.
 */
public interface GovernmentResults {

	/** the government is (still) searching for the fugitive. */
	void onSearching();
	
	/** the government has found the fugitive. */
	void onFound(int distance);
	
	/** the government pursuits the fugitive. */
	void onPursuit(int distance);
	
	/** the government captures the fugitive. Game over, you lose. */
	void onCaptured();	
	
	/** the government is convinced of the innocence of the fugitive. Game over, you Win !*/
	void onIsInnocent();
	
}
