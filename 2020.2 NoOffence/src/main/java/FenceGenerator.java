import java.util.ArrayList;
import java.util.List;

public class FenceGenerator {
	private final List<House> houses;
	private final List<House> fence;

	public FenceGenerator(List<House> houses) {
		this.houses = houses;
		this.fence = computeShortestFence();
	}

	public List<House> getFenceList() {
		return fence;
	}

	public List<House> computeShortestFence() {
		int startIndex = startWithLeftMostHouse(houses);
		return computeShortestFence(startIndex);
	}

	public List<House> computeShortestFence(int startIndex) {
		List<House> result = new ArrayList<>();
		result.addAll(createFence(houses, startIndex));
		return result;
	}

	public int startWithLeftMostHouse(List<House> list) {
		int result = -1;
		// Task 1. ce n'est pas difficile! Find the leftmost house in the list

		// ...

		return result;
	}


	private List<House> createFence(List<House> houses, int startHouse) {

		List<House> fence = new ArrayList<House>();

		// Hint: no need to change this method

		// Step Un: Start with a leftmost house, keep moving counterclockwise until we
		// reach the start house again
		int currentHouse = startHouse;
		do {
			fence.add(houses.get(currentHouse));

			// Step Deux: generate the new base house which is the most counterclockwise
			// with respect to currentHouse.
			currentHouse = computeMostCounterClockWiseHouse(currentHouse);

			// Step Trois: if not start house, then use new current house in the next
			// iteration.
		} while (startHouse != currentHouse);

		return fence;
	}

	private int computeMostCounterClockWiseHouse(int fromHouse) {
		int bestHouse = nextHouse(fromHouse);
		// Task 2. Viva la France! Find the leftmost house in the area compared to your
		// base

		// ...
		return bestHouse;
	}
	

	/**
	 * Compare house most left of the current base (via the cross-product length of
	 * the vectors)
	 */
	public boolean isMoreCounterClockWise(int from, int test, int best) {
		// Task 3. Tres difficile! Get the vectors' coordinates. Calculate the cross
		// product.
		
		House fromHouse = houses.get(from);
		House testHouse = houses.get(test);
		House bestHouse = houses.get(best);


		return false;
	}
	
	private int nextHouse(int input) {
		return (input + 1) % houses.size();
	}

}
