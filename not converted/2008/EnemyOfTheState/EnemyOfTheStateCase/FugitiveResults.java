/**
 * Tells the fugitive the results of his actions. 
 */
public interface FugitiveResults {

    /**
     * Tells the Fugitive that he has his search was successful and that
     * he has found an evidence object. 
     * @param obj the object found.
     */
	void onObjectFound(EvidenceObject obj);
	
}
