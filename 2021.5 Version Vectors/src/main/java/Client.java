import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Client {

	private List<DistributedMapNode<String, Integer>> nodes = new ArrayList<>();

	public Client(List<DistributedMapNode<String, Integer>> nodes) {
		this.nodes = nodes;
	}

	public void put(String key, Integer value) throws IOException {
		System.out.println("[client] Trying to write " + key + "=" + value + ", picking undetermined primary node");
		DistributedMapNode<String, Integer> primaryNode = nodes.get(0);
		put(key, value, primaryNode);
	}

	public void put(String key, Integer value, DistributedMapNode<String, Integer> primaryNode) throws IOException {
		System.out.println("[client] Trying to write " + key + "=" + value + ", using " + primaryNode + " as primaryNode");
		List<VersionedValue<Integer>> values = primaryNode.get(key);
		VersionVector vv;
		if (values == null || values.size() == 0) {
			System.out.println("[client]   No previous version available, making new versioned value");
			vv = new VersionVector();
		} else if (values.size() > 1) {
			System.out.println("[client]   Found multiple previous versions, need to resolve conflict");
			// multiple concurrent versions available.
			VersionedValue<Integer> oldValue = resolveConflict(key);
			vv = oldValue.getVersion();
		} else {
			vv = values.get(0).getVersion();
			System.out.println("[client]   Found previous, single, version: " + vv);
		}

		put(primaryNode, key, value, vv);
	}

	private void put(DistributedMapNode<String, Integer> primaryNode, String key, Integer value, VersionVector version)
			throws IOException {
		System.out.println("[client] Trying to write " + key + "=" + value + ", using " + primaryNode + " as primaryNode");
		VersionedValue<Integer> newValue = primaryNode.putAsPrimary(key, value, version);

		for (DistributedMapNode<String, Integer> node : nodes) {
			if (node != primaryNode) {
				try {
					node.put(key, newValue);
				} catch (IOException e) {
					System.out.println("[client] updating node " + node + " failed: " + e.getMessage());
				}
			}
		}
		System.out.println("[client] new value: " + newValue);
	}

	private VersionedValue<Integer> resolveConflict(String key) {
		// so there were multiple versions at the primary node, maybe we can determine
		// causality if we have versions from all nodes

		List<VersionedValue<Integer>> uniqueVersions = nodes.stream().flatMap(n -> {
			try {
				return n.get(key).stream();
			} catch (IOException e) {
				return Stream.empty(); // no response, so no input
			}
		}).distinct().collect(Collectors.toList());

		System.out.println("[client]     resolving conflict by looking at full cluster: " + uniqueVersions);
		for (int i = 0; i < uniqueVersions.size(); i++) {
			VersionedValue<Integer> vv = uniqueVersions.get(i);
			uniqueVersions.removeAll(findOlderVersions(vv, uniqueVersions));
		}

		if (uniqueVersions.size() == 1) {
			System.out.println("[client]     --> resolved to: " + uniqueVersions.get(0));
			return uniqueVersions.get(0);
		} else {
			System.out.println("[client]     --> still multiple concurrent versions: " + uniqueVersions);
			return resolveConflict(uniqueVersions);
		}

	}

	private Collection<?> findOlderVersions(VersionedValue<Integer> value, List<VersionedValue<Integer>> versions) {
		return versions.stream().filter(v -> v.isOlderThan(value)).collect(Collectors.toList());
	}

	private VersionedValue<Integer> resolveConflict(List<VersionedValue<Integer>> values) {
		System.out.println("[client]     resolving conflict manually, by determining max value");

		VersionedValue<Integer> max = null;
		for (VersionedValue<Integer> value : values) {
			System.out.println("[client]     found version: " + value);
			if (max == null || value.getValue() > max.getValue()) {
				max = value;
			}
		}
		System.out.println("[client]     --> manually resolved to max: " + max);

		return max;
	}

}
