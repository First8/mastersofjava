
/**
 * Represents a versioned value. 
 * Contains a VersionVector that retains all known versions at each node. 
 */
public class VersionedValue<T> {
	private T value;
	private VersionVector versionVector;

	public VersionedValue(T value, VersionVector versionVector) {
		this.value = value;
		this.versionVector = versionVector;
	}
	
	public VersionVector getVersion() {
		return versionVector;
	}
	
	public T getValue() {
		return value;
	}
	
	public boolean isOlderThan(VersionedValue<T> other) {
		System.out.println("    comparing " + versionVector + " with " + other.versionVector + ": " + VersionVector.compare(versionVector, other.versionVector));
		return VersionVector.compare(versionVector, other.versionVector)==Ordering.BEFORE;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result + ((versionVector == null) ? 0 : versionVector.hashCode());
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
		VersionedValue<T> other = (VersionedValue<T>) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (versionVector == null) {
			if (other.versionVector != null)
				return false;
		} else if (!versionVector.equals(other.versionVector))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[value=" + value + ", versionVector=" + versionVector + "]";
	}

}