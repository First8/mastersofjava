import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FailureWithoutConflict {
	private static final DistributedMapNode<String, Integer> BLUE = new DistributedMapNode<>("blue");
	private static final DistributedMapNode<String, Integer> GREEN = new DistributedMapNode<>("green");
	private static final DistributedMapNode<String, Integer> RED = new DistributedMapNode<>("red");

	private List<DistributedMapNode<String, Integer>> nodes;
	private Client client;

	@Before
	public void setup() {
		nodes = new ArrayList<>();
		nodes.add(BLUE);
		nodes.add(GREEN);
		nodes.add(RED);
		client = new Client(nodes);
	}

	@Test
	public void redFailing() throws IOException {

		RED.setFailed(true);
		client.put("amount", 7, BLUE);
		client.put("amount", 8, BLUE);
		RED.setFailed(false);

		client.put("amount", 9, BLUE);
		

		List<VersionedValue<Integer>> vvsBlue = BLUE.get("amount");
		assertEquals(1, vvsBlue.size());
		assertEquals(9, vvsBlue.get(0).getValue().intValue());

		List<VersionedValue<Integer>> vvsGreen = GREEN.get("amount");
		assertEquals(1, vvsGreen.size());
		assertEquals(9, vvsGreen.get(0).getValue().intValue());

		List<VersionedValue<Integer>> vvsRed = RED.get("amount");
		assertEquals(1, vvsRed.size());
		assertEquals(9, vvsRed.get(0).getValue().intValue());
	}
	
	
	@Test
	public void whyIsRedConflictingInThisTestINeedCoffee() throws IOException {

		client.put("amount", 7, BLUE);
		RED.setFailed(true);
		client.put("amount", 8, GREEN);
		RED.setFailed(false);

		client.put("amount", 9, GREEN);
		

		List<VersionedValue<Integer>> vvsBlue = BLUE.get("amount");
		assertEquals(1, vvsBlue.size());
		assertEquals(9, vvsBlue.get(0).getValue().intValue());

		List<VersionedValue<Integer>> vvsGreen = GREEN.get("amount");
		assertEquals(1, vvsGreen.size());
		assertEquals(9, vvsGreen.get(0).getValue().intValue());

		List<VersionedValue<Integer>> vvsRed = RED.get("amount");
		assertEquals(1, vvsRed.size());
		assertEquals(9, vvsRed.get(0).getValue().intValue());
	}	

}
