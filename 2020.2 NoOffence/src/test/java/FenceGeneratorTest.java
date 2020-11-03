import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class FenceGeneratorTest {

	@Test
	public void testReadPointNames() {
		String readNames = City.readPointNames(City.createVillageNouvion().getHouseLocationList());
		Assert.assertEquals(readNames, "A B C D E F G H I J K L M");
	}

	@Test
	public void testExpectedFencesForNouvion() {
		City repository = City.createVillageNouvion();
		String expectedWallPointNames = "A D H K M I C";
		repository.printVillage("Nouvion");
		comparePointsWithExpected(expectedWallPointNames, repository.getHouseLocationList());
		FenceGenerator generator = new FenceGenerator(repository.getHouseLocationList());
		generator.computeShortestFence();
		Assert.assertEquals(32, FenceUtil.computeTotalFenceDistance(generator.getFenceList()));
	}

	@Test
	public void testExpectedFencesForNouvionViaDifferentStartPoint() {
		City repository = City.createVillageNouvion();
		String expectedWallPointNames = "A D H K M I C";
		comparePointsWithExpected(expectedWallPointNames, repository.getHouseLocationList());
		FenceGenerator generator = new FenceGenerator(repository.getHouseLocationList());
		generator.computeShortestFence(3);
		Assert.assertEquals(32, FenceUtil.computeTotalFenceDistance(generator.getFenceList()));
	}

	@Test
	public void testExpectedFenceForCastleNearbyNouvion() {
		City repository = City.createCastleNearbyNouvion();
		String expectedWallPointNames = "A B C D E F G H";
		repository.printVillage("Castle Nearby Nouvion");
		comparePointsWithExpected(expectedWallPointNames, repository.getHouseLocationList());
		FenceGenerator generator = new FenceGenerator(repository.getHouseLocationList());
		generator.computeShortestFence(3);
		Assert.assertEquals(10, FenceUtil.computeTotalFenceDistance(generator.getFenceList()));
	}

	@Test
	public void testExpectedFenceForEntireRegion() {
		City repository = City.createCompleteAreaOfParisIndication();
		String expectedWallPointNames = "s w n e";
		comparePointsWithExpected(expectedWallPointNames, repository.getHouseLocationList());
		FenceGenerator generator = new FenceGenerator(repository.getHouseLocationList());
		generator.computeShortestFence();
		Assert.assertTrue(2000 == FenceUtil.computeTotalFenceDistance(generator.getFenceList()));
	}

	protected static void comparePointsWithExpected(String expectedWallPointNames, List<House> houseLocations) {
		FenceGenerator generator = new FenceGenerator(houseLocations);

		List<House> actualWall = generator.computeShortestFence();
		String actualWallPointNames = City.readPointNames(actualWall);
		System.out.println("expected: " + expectedWallPointNames);
		System.out.println("actual: " + actualWallPointNames);
		Assert.assertEquals(expectedWallPointNames, actualWallPointNames);

	}

}
