import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a node holding a map.
 * Each node keeps a list of all known versions of a value (that are not completely obsolete). As such, a get(key) may return multiple versioned values.
 * If you want to write a value, you need to put the key with the value and specify a version vector that will be checked if it is still current.
 * Use the putAsPrimary() function for writing to this node as a primary node, use put() for writing to secondary nodes. 
 * 
 * @param <K> key type 
 * @param <V> value type
 */
public class DistributedMapNode<K, V> {
	private String id;
	private Map<K, List<VersionedValue<V>>> kv = new HashMap<>();
	private boolean failed = false; // simplistic state that assumes a node is completely failing (we do not simulate partial network failure

	public DistributedMapNode(String id) {
		this.id = id;
	}
	
	public void setFailed(boolean failed) {
		this.failed = failed;
	}
	
	private void checkFailed() throws IOException {
		if (failed) {
			throw new IOException("node " + id + " is unavailable");
		}
	}
	
	
	public List<VersionedValue<V>> get(K key) throws IOException {
		checkFailed();
		return kv.get(key);
	}

	public VersionedValue<V> putAsPrimary(K key, V value, VersionVector existingVersion) throws IOException {
		checkFailed();
		VersionVector newVersion = existingVersion.increment(id);
		VersionedValue<V> versionedValue = new VersionedValue<V>(value, newVersion);
		put(key, versionedValue);
		return versionedValue;
	}

	public void put(K key, VersionedValue<V> newValue) throws IOException {
		checkFailed();
		
		List<VersionedValue<V>> existingValues = kv.get(key);
		System.out.println("[node " + id + "] before put of key "+key+" with versionedValue " + newValue + ", the known version values are: " + existingValues);
		if (existingValues == null) {
			existingValues = new ArrayList<>();
		}

		rejectIfOldWrite(key, newValue, existingValues);
		List<VersionedValue<V>> newValues = merge(newValue, existingValues);
		kv.put(key, newValues);
		System.out.println("[node " + id + "] after put of key "+key+", the known version values are: " + newValues);
	}

	// If the newValue is older than existing one reject it.
	private void rejectIfOldWrite(K key, VersionedValue<V> newValue, List<VersionedValue<V>> existingValues) {
		for (VersionedValue<V> existingValue : existingValues) {
			if (newValue.isOlderThan(existingValue)) {
				throw new ObsoleteVersionException("Obsolete version for key '" + key + "': " + newValue.getVersion());
			}
		}
	}

	/**
	 * Add the newValue to the known versionedValues, removing completely dominated older versions.
	 * @param newValue the new versioned value to add
	 * @param existingValues current versions
	 * @return a new list of versioned values to retain
	 */
	private List<VersionedValue<V>> merge(VersionedValue<V> newValue, List<VersionedValue<V>> existingValues) {
		List<VersionedValue<V>> retainedValues = existingValues.stream().filter(v -> !v.isOlderThan(newValue))
		.collect(Collectors.toList());
		retainedValues.add(newValue);
		return retainedValues;
	}


	@Override
	public String toString() {
		return id;
	}

}
