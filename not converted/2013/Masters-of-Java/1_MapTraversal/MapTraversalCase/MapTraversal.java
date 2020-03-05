import java.util.*;

/**
 * Interface specification for the MapTraversal Masters of Java assignment.
 * 
 * @author Patrick Kik
 */
public interface MapTraversal {
	
	/**
	 * Gets the key from the value.
	 * 
	 * @param map a multidimensional map
	 * @param key a key
	 * @return a String or null when the key could not be found.
	 */
	String getValue(Map<String, Object> map, String key);

}
