/**
 * These represent the actions the Fugitive can perform.
 * Note that the Fugitive can only perform an action after the Government 
 * has performed one.
 */
public interface FugitiveAction {

    /**
     * Attempts to increase the distance between the Government agents and the Fugitive.
     * Only applicable if the Government is in Pursuit of the Fugitive.
     */
	void doRun();
	
	/**
	 * If the distance between the Government agents and the Fugitive is >=3 the
	 * fugitive may hide. 
	 */
	void doHide();
	
	/**
	 * When hidden the Fugitive may go to an Evidence location. 
	 */
	void doGoToEvidenceLocation();
	
	/**
	 * When at an Evidence Location the Fugitive may search for evidence.
	 */
	void doSearch();
	
	/**
	 * When Hidden or at an Evidence Location the Fugitive may prove his innocence
	 * by using an previously found, unused EvidenceObject.
	 * @param evidenceObject the evidence to use.
	 */
	void doUse(EvidenceObject evidenceObject);
	
}
