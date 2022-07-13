import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Represents a vector containing all known versions (of some value) at a node.
 */
class VersionVector {
	// maps node id to versions
	private final Map<String, Long> versions;

	public VersionVector() {
		this(new HashMap<>());
	}

	public VersionVector(Map<String, Long> versions) {
		this.versions = versions;
	}

	public Map<String, Long> getVersions() {
		return versions;
	}

	/**
	 * Create a newer version for a specific node.
	 * @param nodeId the node to create a later version for
	 * @return a new vector that is guaranteed to be later at the given node
	 */
	public VersionVector increment(String nodeId) {
		Map<String, Long> versions = new HashMap<>();
		versions.putAll(this.versions);
		Long version = versions.get(nodeId);
		if (version == null) {
			version = 1L;
		} else {
			version = version + 1L;
		}
		versions.put(nodeId, version);
		return new VersionVector(versions);
	}

	/**
	 * Compares two version vectors.
	 * @param v1 the first vector
	 * @param v2 the second vector
	 * @return EQUAL if they are the same, CONCURRENT if they are concurrent, AFTER if  v1 is later than v2, BEFORE if v1 is earlier than v2.
	 */
	public static Ordering compare(VersionVector v1, VersionVector v2) {
		if (v1 == null || v2 == null)
			throw new IllegalArgumentException("Can't compare null version vectors!");
		
		
		// since the vectors are independent, they may be both "bigger" than the other, resulting in a concurrent ordering (meaning there is no order)
		boolean v1Bigger = false;
		boolean v2Bigger = false;

		Set<String> v1Nodes = v1.getVersions().keySet();
		Set<String> v2Nodes = v2.getVersions().keySet();
		Set<String> commonNodes = getCommonNodes(v1Nodes, v2Nodes);
	
		// if v1 has more nodes than common nodes
		// v1 has clocks that v2 does not
		if (v1Nodes.size() > commonNodes.size()) {
			v1Bigger = true;
		}
		// if v2 has more nodes than common nodes
		// v2 has clocks that v1 does not
		if (v2Nodes.size() > commonNodes.size()) {
			v2Bigger = true;
		}
		
		// compare the common parts
		for (String nodeId : commonNodes) {
			// no need to compare more
			if (v1Bigger && v2Bigger) {
				break;
			}
			long v1Version = v1.getVersions().get(nodeId);
			long v2Version = v2.getVersions().get(nodeId);
			if (v1Version > v2Version) {
				v1Bigger = true;
			} else if (v1Version < v2Version) {
				v2Bigger = true;
			}
		}

		if (!v1Bigger && !v2Bigger)
			return Ordering.EQUAL;
		else if (v1Bigger && !v2Bigger)
			return Ordering.AFTER;
		else if (!v1Bigger && v2Bigger)
			return Ordering.BEFORE;
		else
			return Ordering.CONCURRENT;
	}

	private static Set<String> getCommonNodes(Set<String> v1Nodes, Set<String> v2Nodes) {
		Set<String> commonNodes = new HashSet<String>(v1Nodes);
		commonNodes.retainAll(v2Nodes);
		return commonNodes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((versions == null) ? 0 : versions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VersionVector other = (VersionVector) obj;
		if (versions == null) {
			if (other.versions != null)
				return false;
		} else if (!versions.equals(other.versions))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return versions.toString();
	}
	
	
}