/**
 * A diplomat of a certain country who speaks certain languages.
 * The country of the diplomat has relations with other countries. 
 */

public interface Diplomat {

	/** @return the country of the diplomat. */
	String getCountry();
	/** @return the languages the diplomat speaks. */
	String[] getLanguages();
	/** @return the countries the diplomats country holds relations with. */ 
	String[] getRelations();
	/** @return true if this diplomat is the leader. */
	boolean isLeader();

	/**
	 * @param d a diplomat.
	 * @return true if the specified diplomat has at least one language in common with this diplomat.
	 */
	boolean understands(Diplomat d);

	/**
	 * @param d a diplomat
	 * @return true if the specified diplomats country has formal relations with this diplomats country.
	 */
	boolean hasDiplomaticRelationsWith(Diplomat d);
	
}
